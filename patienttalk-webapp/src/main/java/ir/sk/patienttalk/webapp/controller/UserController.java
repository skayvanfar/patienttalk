package ir.sk.patienttalk.webapp.controller;

import ir.sk.patienttalk.common.service.GenericManager;
import ir.sk.patienttalk.model.domain.User;
import ir.sk.patienttalk.webapp.dto.UserSearchData;
import ir.sk.patienttalk.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 9/8/2017.
 */
@Controller
@RequestMapping("/users")
public class UserController extends GenericController<User, UserSearchData, Long> {

    private UserService userService;

    /**
     * Constructor that takes in a class to see which type of entity to control.
     * Use this constructor when subclassing.
     *
     * @param userService
     */
    @Autowired
    public UserController(UserService userService) {
        super(User.class, userService);
        this.userService = userService;
    }

}
