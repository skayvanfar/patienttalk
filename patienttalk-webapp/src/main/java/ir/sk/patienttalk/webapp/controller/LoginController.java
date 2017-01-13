package ir.sk.patienttalk.webapp.controller;

import ir.sk.patienttalk.common.persistence.PersistenceException;
import ir.sk.patienttalk.webapp.dto.LoginData;
import ir.sk.patienttalk.webapp.dto.SignupData;
import ir.sk.patienttalk.webapp.service.UserService;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/6/2017.
 */
@Controller
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;

    @InitBinder
    protected void initBinder(HttpServletRequest request,
                              ServletRequestDataBinder binder) throws Exception {
        // bind empty strings as null
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String home(HttpServletRequest request, Map<String, Object> model) throws PersistenceException {
        String redirect = request.getParameter("redirect");
        if (redirect != null)
            return "redirect:/redirectLogin?redirect=" + redirect;
        return defaultHomeModel(model, new SignupData(), new LoginData());
    }

    @RequestMapping(value = {"/login", "/login/p"})
    public String login(HttpServletRequest request, Map<String, Object> model, @Valid LoginData data) throws PersistenceException {
        String redirect = request.getParameter("redirect");
        if (redirect != null)
            return "redirect:/redirectLogin?redirect=" + redirect;
        return defaultHomeModel(model, new SignupData(), new LoginData(true));
    }

    public static String defaultHomeModel(Map<String, Object> model, SignupData signupData, LoginData loginData) throws PersistenceException {
        model.put("signupData", signupData);
        model.put("loginData", loginData);
        if (signupData.getRedirect() != null || loginData.getRedirect() != null)
            return "redirectLogin";
        return "home";
    }

    @RequestMapping(value = {"/redirectLogin"})
    public String redirectLogin(@RequestParam(value = "redirect") String redirect,
                                Map<String, Object> model) throws PersistenceException {
        model.put("signupData", new SignupData(redirect));
        model.put("loginData", new LoginData(redirect));
        return "redirectLogin";
    }

    @RequestMapping(value = {"/signup"}, method = RequestMethod.GET)
    public String signupGET(Map<String, Object> model) throws PersistenceException {
        final SignupData data = new SignupData();
        data.setHasError(true);
        return defaultHomeModel(model, data, new LoginData());
    }

    @RequestMapping(value = {"/signup"}, method = RequestMethod.POST)
    public String signupPOST(@Valid SignupData data, BindingResult bindingResult,
                             Map<String, Object> model) throws PersistenceException {
        boolean hasError = false;

        // Validations
        if (bindingResult.hasErrors()) {
            data.setHasError(true);
            hasError = true;
        }
        if (userService.getObjectByPropertyEqualTo("username", data.getUsername()) != null) {
            bindingResult.rejectValue("username", "", "repeated username");
            data.setHasError(true);
            hasError = true;
        }
        if (!data.getPassword1().equals(data.getPassword2())) {
            bindingResult.rejectValue("password2", "", "Not equals");
            data.setHasError(true);
            hasError = true;
        }

        if (!hasError)
            userService.signup(data);
        return defaultHomeModel(model, data, new LoginData());
    }

    @RequestMapping(value = "/favicon.ico")
    public String favicon() {
        return "redirect:/resources/img/favicon.ico";
    }

    @RequestMapping(value="/loginfailed", method = RequestMethod.GET)
    public String loginerror(Model model) {
        model.addAttribute("error", "true");
        return "/home";
    }

    @RequestMapping(value="/logout/p", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/home";//You can redirect wherever you want, but generally it's a good practice to show login screen again.

      //  return "home";
    }

    public static String anonymousHome(Map<String, Object> model) throws PersistenceException {
        return defaultHomeModel(model,
                new SignupData(), new LoginData());
    }
}
