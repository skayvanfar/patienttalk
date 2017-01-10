package ir.sk.patienttalk.webapp.dto;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/10/2017.
 */
public class LoginData {

    private String username;

    private String password;

    private String redirect;

    private boolean hasError;

    public LoginData() {
        hasError = false;
    }

    public LoginData(String redirect) {
        this.redirect = redirect;
        hasError = false;
    }

    public LoginData(boolean hasError) {
        this.hasError = hasError;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

}
