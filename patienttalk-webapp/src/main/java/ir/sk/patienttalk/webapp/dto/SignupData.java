package ir.sk.patienttalk.webapp.dto;

import ir.sk.patienttalk.model.domain.EmailAddress;
import ir.sk.patienttalk.model.domain.User;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/10/2017.
 */
public class SignupData {

    @Valid
    private ProfileData profile;

    @NotNull
    @Size(min = 5, max = 50)
    @Pattern(regexp = "^[a-z]+[a-z0-9_\\.-]*[a-z]+$")
    private String username;

    @NotNull
    @Size(min = 6, max = 50)
    private String password1;

    @NotNull
    @Size(min = 6, max = 50)
    private String password2;

    @NotNull
    @Pattern(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    private String mail;

    private String redirect;

    private boolean hasError;

    private boolean succeed;

    public SignupData() {
        hasError = succeed = false;
        profile = new ProfileData();
    }

    public SignupData(String redirect) {
        this.redirect = redirect;
        hasError = succeed = false;
        profile = new ProfileData();
    }

    public User fill(User user) {
        if(user == null)
            user = new User();
        user.setProfile(profile.fill(null)); // TODO: 1/10/2017  
        user.getProfile().setUser(user);
        user.setUsername(username);
        user.setPassword(password1);
        user.getProfile().setEmailAddress(new EmailAddress(mail));
        return user;
    }

    public ProfileData getProfile() {
        return profile;
    }

    public void setProfile(ProfileData profile) {
        this.profile = profile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public boolean isSucceed() {
        return succeed;
    }

    public void setSucceed(boolean succeed) {
        this.succeed = succeed;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

}
