package ir.sk.patienttalk.model.domain;

import ir.sk.patienttalk.common.persistence.jpa.EntityBase;
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

import org.springframework.format.annotation.DateTimeFormat;
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

    @Size(min = 5, max = 40, message="{Size.User.username.validation}")
    @Column(name = "USERNAME", nullable = false, length = 40)
    @Basic
    @NotNull(message= "{NotNull.User.username.validation}")
    private String username;

    /*@Size(max = 50, message="{Size.User.code.validation}")
    @Column(name = "CODE", nullable = false, length = 50, unique = true)
    @Basic
    private String code;*/

    @Column(name = "PASSWORD", nullable = false, length = 100)
    @Basic
    @NotNull(message= "{NotNull.User.password.validation}")
    @Size(max = 100, message="{Size.User.password.validation}")
    private String password;

    @Transient
    private boolean onlineStatus;

    @ElementCollection(fetch = FetchType.EAGER, targetClass = UserRole.class)
    @Fetch(FetchMode.SELECT)
    @CollectionTable(name = "TBL_PT_USER_ROLE", joinColumns = @JoinColumn(name = "FK_USER_ID"))
    @Column(name = "USER_ROLE")
    private Set<UserRole> userRoles;

    @Embedded
    private Profile profile;

 //   private Image image;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_MOOD_ID")
    private Mood mood;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<Thread> threads;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<UserGivenEmoji> userGivenEmojis;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<UserReceivedEmoji> userReceivedEmojis;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<Trophy> trophies;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<UserWatchedThread> userWatchedThreads;

    public User() {
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

   /* public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }*/

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        //   this.password = Utils.hashString(password);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        this.password = hashedPassword;
    }

    public boolean isOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(boolean onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Mood getMood() {
        return mood;
    }

    public void setMood(Mood mood) {
        this.mood = mood;
    }

    public List<Thread> getThreads() {
        return threads;
    }

    public void setThreads(List<Thread> threads) {
        this.threads = threads;
    }

    public List<UserGivenEmoji> getUserGivenEmojis() {
        return userGivenEmojis;
    }

    public void setUserGivenEmojis(List<UserGivenEmoji> userGivenEmojis) {
        this.userGivenEmojis = userGivenEmojis;
    }

    public List<UserReceivedEmoji> getUserReceivedEmojis() {
        return userReceivedEmojis;
    }

    public void setUserReceivedEmojis(List<UserReceivedEmoji> userReceivedEmojis) {
        this.userReceivedEmojis = userReceivedEmojis;
    }

    public List<Trophy> getTrophies() {
        return trophies;
    }

    public void setTrophies(List<Trophy> trophies) {
        this.trophies = trophies;
    }

    public List<UserWatchedThread> getUserWatchedThreads() {
        return userWatchedThreads;
    }

    public void setUserWatchedThreads(List<UserWatchedThread> userWatchedThreads) {
        this.userWatchedThreads = userWatchedThreads;
    }

    public Set<UserRole> getUserRoles() {
        if(userRoles==null)
            userRoles = new HashSet<>();
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public boolean hasUserRole(UserRole userRole) {
        for(UserRole role : userRoles)
            if(role == userRole)
                return true;
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (onlineStatus != user.onlineStatus) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (userRoles != null ? !userRoles.equals(user.userRoles) : user.userRoles != null) return false;
        if (profile != null ? !profile.equals(user.profile) : user.profile != null) return false;
        if (mood != null ? !mood.equals(user.mood) : user.mood != null) return false;
        if (threads != null ? !threads.equals(user.threads) : user.threads != null) return false;
        if (userGivenEmojis != null ? !userGivenEmojis.equals(user.userGivenEmojis) : user.userGivenEmojis != null)
            return false;
        if (userReceivedEmojis != null ? !userReceivedEmojis.equals(user.userReceivedEmojis) : user.userReceivedEmojis != null)
            return false;
        if (trophies != null ? !trophies.equals(user.trophies) : user.trophies != null) return false;
        return userWatchedThreads != null ? userWatchedThreads.equals(user.userWatchedThreads) : user.userWatchedThreads == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (onlineStatus ? 1 : 0);
        result = 31 * result + (userRoles != null ? userRoles.hashCode() : 0);
        result = 31 * result + (profile != null ? profile.hashCode() : 0);
        result = 31 * result + (mood != null ? mood.hashCode() : 0);
        result = 31 * result + (threads != null ? threads.hashCode() : 0);
        result = 31 * result + (userGivenEmojis != null ? userGivenEmojis.hashCode() : 0);
        result = 31 * result + (userReceivedEmojis != null ? userReceivedEmojis.hashCode() : 0);
        result = 31 * result + (trophies != null ? trophies.hashCode() : 0);
        result = 31 * result + (userWatchedThreads != null ? userWatchedThreads.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", onlineStatus=" + onlineStatus +
                ", userRoles=" + userRoles +
                ", profile=" + profile +
                ", mood=" + mood +
                ", threads=" + threads +
                ", userGivenEmojis=" + userGivenEmojis +
                ", userReceivedEmojis=" + userReceivedEmojis +
                ", trophies=" + trophies +
                ", userWatchedThreads=" + userWatchedThreads +
                '}';
    }
}
