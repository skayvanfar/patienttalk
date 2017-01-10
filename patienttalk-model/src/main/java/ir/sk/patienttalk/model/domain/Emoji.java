package ir.sk.patienttalk.model.domain;

import ir.sk.patienttalk.common.persistence.jpa.EntityBase;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/5/2017.
 */
@Entity
@Table(name = "TBL_PT_EMOJI", schema = "", catalog = "PATIENTTALK")
public class Emoji extends EntityBase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PK_EMOJI_ID", unique = true, nullable = false)
    private long id;

    @Size(min = 5, max = 40)
    @Column(name = "NAME", nullable = false, insertable = true, updatable = true, length = 40)
    @Basic
    @NotNull
    private String name;

    @OneToMany(mappedBy = "emoji", cascade = {CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<PostHasEmoji> postHasEmojis;

    @OneToMany(mappedBy = "emoji", cascade = {CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<UserGivenEmoji> userGivenEmojis;

    @OneToMany(mappedBy = "emoji", cascade = {CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<UserReceivedEmoji> userReceivedEmojis;

    public Emoji() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PostHasEmoji> getPostHasEmojis() {
        return postHasEmojis;
    }

    public void setPostHasEmojis(List<PostHasEmoji> postHasEmojis) {
        this.postHasEmojis = postHasEmojis;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Emoji emoji = (Emoji) o;

        if (id != emoji.id) return false;
        if (name != null ? !name.equals(emoji.name) : emoji.name != null) return false;
        if (postHasEmojis != null ? !postHasEmojis.equals(emoji.postHasEmojis) : emoji.postHasEmojis != null)
            return false;
        if (userGivenEmojis != null ? !userGivenEmojis.equals(emoji.userGivenEmojis) : emoji.userGivenEmojis != null)
            return false;
        return userReceivedEmojis != null ? userReceivedEmojis.equals(emoji.userReceivedEmojis) : emoji.userReceivedEmojis == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (postHasEmojis != null ? postHasEmojis.hashCode() : 0);
        result = 31 * result + (userGivenEmojis != null ? userGivenEmojis.hashCode() : 0);
        result = 31 * result + (userReceivedEmojis != null ? userReceivedEmojis.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Emoji{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", postHasEmojis=" + postHasEmojis +
                ", userGivenEmojis=" + userGivenEmojis +
                ", userReceivedEmojis=" + userReceivedEmojis +
                '}';
    }
}
