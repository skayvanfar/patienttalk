package ir.sk.patienttalk.model.domain;

import ir.sk.patienttalk.common.persistence.jpa.EntityBase;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/5/2017.
 */
@Entity
@Table(name = "TBL_PT_FORUM", schema = "", catalog = "PATIENTTALK")
public class Forum extends EntityBase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PK_FORUM_ID", unique = true, nullable = false)
    private long id;

    @Size(min = 5, max = 40, message="{Size.Forum.name.validation}")
    @Column(name = "NAME", nullable = false, length = 40)
    @Basic
    @NotNull(message= "{NotNull.Forum.name.validation}")
    private String name;

    @OneToMany(mappedBy = "forum", cascade = {CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<SubForum> subForums = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_CHANNEL_ID", nullable = false)
    @NotNull(message= "{NotNull.Forum.channel.validation}")
    private Channel channel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_WATCHED_USER_ID", nullable = false)
    @NotNull(message= "{NotNull.Forum.watchedUser.validation}")
    private User watchedUser;

    public Forum() {
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

    public List<SubForum> getSubForums() {
        return subForums;
    }

    public void setSubForums(List<SubForum> subForums) {
        this.subForums = subForums;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public User getWatchedUser() {
        return watchedUser;
    }

    public void setWatchedUser(User watchedUser) {
        this.watchedUser = watchedUser;
    }

    // A convenience method simplifies relationship management
    public void addSubForum(SubForum subForum) {
        if (subForum == null)
            throw new NullPointerException("Can't add null SubForum"); // Be defensive
        if (subForum.getForum() != null)
            throw new IllegalStateException("SubForum is already assigned to an Forum");
        getSubForums().add(subForum);
        subForum.setForum(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Forum forum = (Forum) o;

        if (id != forum.id) return false;
        if (name != null ? !name.equals(forum.name) : forum.name != null) return false;
        if (subForums != null ? !subForums.equals(forum.subForums) : forum.subForums != null) return false;
        if (channel != null ? !channel.equals(forum.channel) : forum.channel != null) return false;
        return watchedUser != null ? watchedUser.equals(forum.watchedUser) : forum.watchedUser == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (subForums != null ? subForums.hashCode() : 0);
        result = 31 * result + (channel != null ? channel.hashCode() : 0);
        result = 31 * result + (watchedUser != null ? watchedUser.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Forum{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subForums=" + subForums +
                ", channel=" + channel +
                ", watchedUser=" + watchedUser +
                '}';
    }
}
