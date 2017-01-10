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
@Table(name = "TBL_PT_POST", schema = "", catalog = "PATIENTTALK")
public class Post extends EntityBase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PK_POST_ID", unique = true, nullable = false)
    private long id;

    @Size(min = 5, max = 500)
    @Column(name = "MESSAGE", nullable = false, insertable = true, updatable = true, length = 500)
    @Basic
    @NotNull
    private String message;

/*    @OneToMany(mappedBy = "post", cascade = {CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<Emoji> emojis;*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_THREAD_ID", nullable = false)
    @NotNull
    private Thread thread;

    @OneToMany(mappedBy = "post", cascade = {CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<PostHasEmoji> postHasEmojis = new ArrayList<>();

    public Post() {
    }

    public long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public List<PostHasEmoji> getPostHasEmojis() {
        return postHasEmojis;
    }

    public void setPostHasEmojis(List<PostHasEmoji> postHasEmojis) {
        this.postHasEmojis = postHasEmojis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (id != post.id) return false;
        if (message != null ? !message.equals(post.message) : post.message != null) return false;
        if (thread != null ? !thread.equals(post.thread) : post.thread != null) return false;
        return postHasEmojis != null ? postHasEmojis.equals(post.postHasEmojis) : post.postHasEmojis == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (thread != null ? thread.hashCode() : 0);
        result = 31 * result + (postHasEmojis != null ? postHasEmojis.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", thread=" + thread +
                ", postHasEmojis=" + postHasEmojis +
                '}';
    }
}
