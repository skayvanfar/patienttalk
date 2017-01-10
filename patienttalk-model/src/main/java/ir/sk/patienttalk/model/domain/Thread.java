package ir.sk.patienttalk.model.domain;

import ir.sk.patienttalk.common.persistence.jpa.EntityBase;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/5/2017.
 */
@Entity
@Table(name = "TBL_PT_THREAD", schema = "", catalog = "PATIENTTALK")
public class Thread extends EntityBase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PK_THREAD_ID", unique = true, nullable = false)
    private long id;

    @Size(min = 5, max = 40)
    @Column(name = "NAME", nullable = false, insertable = true, updatable = true, length = 40)
    @Basic
    @NotNull
    private String name;

    @OneToMany(mappedBy = "thread", cascade = {CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<Post> posts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_SUBFORUM_ID", nullable = false)
    @NotNull
    private SubForum subForum;

    @Column(name = "START_DATE", nullable = false, insertable = true, updatable = true)
    @Basic
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date startDate;

    // How many time has seen
    @Column(name = "VIEW_TIMES", nullable = false, insertable = true, updatable = true)
    @Basic
    private long viewTimes = 0;

    // How many time has replied
    @Column(name = "REPLY_TIMES", nullable = false, insertable = true, updatable = true)
    @Basic
    private long replyTimes = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_USER_ID", nullable = false)
    @NotNull
    private User user;



    @OneToMany(mappedBy = "thread", cascade = {CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<UserWatchedThread> userWatchedThreads;

    public Thread() {
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

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public SubForum getSubForum() {
        return subForum;
    }

    public void setSubForum(SubForum subForum) {
        this.subForum = subForum;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public long getViewTimes() {
        return viewTimes;
    }

    public void setViewTimes(long viewTimes) {
        this.viewTimes = viewTimes;
    }

    public long getReplyTimes() {
        return replyTimes;
    }

    public void setReplyTimes(long replyTimes) {
        this.replyTimes = replyTimes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<UserWatchedThread> getUserWatchedThreads() {
        return userWatchedThreads;
    }

    public void setUserWatchedThreads(List<UserWatchedThread> userWatchedThreads) {
        this.userWatchedThreads = userWatchedThreads;
    }

    // A convenience method simplifies relationship management
    public void addPost(Post post) {
        if (post == null)
            throw new NullPointerException("Can't add null Post"); // Be defensive
        if (post.getThread() != null)
            throw new IllegalStateException("Post is already assigned to an Thread");
        getPosts().add(post);
        post.setThread(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Thread thread = (Thread) o;

        if (id != thread.id) return false;
        if (viewTimes != thread.viewTimes) return false;
        if (replyTimes != thread.replyTimes) return false;
        if (name != null ? !name.equals(thread.name) : thread.name != null) return false;
        if (posts != null ? !posts.equals(thread.posts) : thread.posts != null) return false;
        if (subForum != null ? !subForum.equals(thread.subForum) : thread.subForum != null) return false;
        if (startDate != null ? !startDate.equals(thread.startDate) : thread.startDate != null) return false;
        if (user != null ? !user.equals(thread.user) : thread.user != null) return false;
        return userWatchedThreads != null ? userWatchedThreads.equals(thread.userWatchedThreads) : thread.userWatchedThreads == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (posts != null ? posts.hashCode() : 0);
        result = 31 * result + (subForum != null ? subForum.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (int) (viewTimes ^ (viewTimes >>> 32));
        result = 31 * result + (int) (replyTimes ^ (replyTimes >>> 32));
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (userWatchedThreads != null ? userWatchedThreads.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Thread{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", posts=" + posts +
                ", subForum=" + subForum +
                ", startDate=" + startDate +
                ", viewTimes=" + viewTimes +
                ", replyTimes=" + replyTimes +
                ", user=" + user +
                ", userWatchedThreads=" + userWatchedThreads +
                '}';
    }
}
