package ir.sk.patienttalk.patienttalkmodel.domain;

import ir.sk.patienttalk.patienttalkcommon.persistence.jpa.EntityBase;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/5/2017.
 */
@Entity
@Table(name = "TBL_PT_USER", schema = "", catalog = "PATIENTTALK")
public class User extends EntityBase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PK_USER_ID", unique = true, nullable = false)
    private long id;

    @Size(min = 5, max = 40)
    @Column(name = "NAME", nullable = true, insertable = true, updatable = true, length = 40)
    @Basic
    private String name;

    @Size(min = 5, max = 40)
    @Column(name = "USER_NAME", nullable = false, insertable = true, updatable = true, length = 40)
    @Basic
    @NotNull
    private String username;

    @Size(max = 50)
    @Column(name = "CODE", nullable = false, insertable = true, updatable = true, length = 50, unique = true)
    @Basic
    private String code;

    @Column(name = "PASSWORD", nullable = false, insertable = true, updatable = true, length = 100)
    @Basic
    @NotNull
    @Size(max = 100)
    private String password;

    private GenderEnum genderEnum;
    private NationalityEnum nationality;

    @Column(name = "PHONE", nullable = true, insertable = true, updatable = true, length = 20)
    @Basic
    @Size(min = 4, max = 20)
    private String phone;

    private String location;

    @Column(name = "MAIL", nullable = true, insertable = true, updatable = true, length = 100)
    @Basic
    @Size(min = 5, max = 100)
    private String mail;

    private boolean onlineStatus;

    @ElementCollection(fetch = FetchType.EAGER, targetClass = UserRole.class)
    @Fetch(FetchMode.SELECT)
    @CollectionTable(name = "TBL_JCG_USER_ROLE", joinColumns = @JoinColumn(name = "FK_USER_ID"))
    @Column(name = "USER_ROLE")
    private Set<UserRole> userRoles;

    private String illnessSince;
    private Date memberDate;
    private Date lastSeenDate;
    private Date LastActivityDate;
    private String CauseOfIllness;

 //   private Image image;
    private Mood mood;

    private List<Forum> forums;
    private List<Emoji> givenEmojis;
    private List<Emoji> receivedEmojis;

    private List<Trophy> trophies;

    private List<Forum> watchedForums;
    private List<Thread> watchedThreads;

    public boolean hasUserRole(UserRole userRole) {
        for(UserRole role : userRoles)
            if(role == userRole)
                return true;
        return false;
    }

    public long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
        //   this.password = Utils.hashString(password);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        this.password = hashedPassword;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (mail != null ? !mail.equals(user.mail) : user.mail != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (phone != null ? !phone.equals(user.phone) : user.phone != null) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;

        return true;
    }

    public Set<UserRole> getUserRoles() {
        if(userRoles==null)
            userRoles = new HashSet<>();
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        result = 31 * result + (int) (id ^ (id >>> 32));
        return result;
    }

    public String getFullName() {
        return username;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", code='" + code + '\'' +
                ", phone='" + phone + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}
