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
@Table(name = "TBL_PT_FORUM", schema = "", catalog = "PATIENTTALK")
public class Forum extends EntityBase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PK_FORUM_ID", unique = true, nullable = false)
    private long id;

    @Size(min = 5, max = 40)
    @Column(name = "NAME", nullable = false, insertable = true, updatable = true, length = 40)
    @Basic
    @NotNull
    private String name;

    @OneToMany(mappedBy = "forum", cascade = {CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<SubForum> subForums;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_CHANNEL_ID", nullable = false)
    @NotNull
    private Channel channel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_WATCHED_USER_ID", nullable = false)
    @NotNull
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
}
