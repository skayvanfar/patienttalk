package ir.sk.patienttalk.webapp.dto;

import ir.sk.patienttalk.common.persistence.SearchData;
import ir.sk.patienttalk.common.persistence.jpa.SearchParam;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 9/8/2017.
 */
public class UserSearchData extends SearchData {

    @SearchParam
    private Long id;

    @SearchParam
    private String username;

    @SearchParam
    private String password;

    /*
        private String family;

        private String mail;
    */
    public UserSearchData() {
    }

    public UserSearchData(int pageSize) {
        super(pageSize);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
