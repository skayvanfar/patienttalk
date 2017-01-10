package ir.sk.patienttalk.model.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/6/2017.
 */
@Entity
@Table(name = "TBL_PT_USER_WATCHED_THREAD", schema = "", catalog = "PATIENTTALK")
public class UserWatchedThread implements Serializable {

    // Encapsulates composite key
    @Embeddable
    public static class Id implements Serializable {

        @Column(name = "FK_USER_ID")
        protected Long userId;

        @Column(name = "FK_THREAD_ID")
        protected Long threadId;

        public Id() {
        }

        public Id(Long userId, Long threadId) {
            this.userId = userId;
            this.threadId = threadId;
        }

        public boolean equals(Object o) {
            if (o != null && o instanceof PostHasEmoji.Id) {
                Id that = (Id) o;
                return this.userId.equals(that.userId)
                        && this.threadId.equals(that.threadId);
            }
            return false;
        }

        public int hashCode() {
            return userId.hashCode() + threadId.hashCode();
        }
    }

    // Maps identifier property and composite key columns
    @EmbeddedId
    protected Id id = new Id();

    @Column(name = "ADDED_ON_DATE", nullable = false, updatable = false)
    @Basic
    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    protected Date addedOnDate = new Date();


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_USER_ID", nullable = false, insertable = false, updatable = false)
    @NotNull(message= "{NotNull.UserWatchedThread.user.validation}")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_THREAD_ID", nullable = false, insertable = false, updatable = false)
    @NotNull(message= "{NotNull.UserWatchedThread.thread.validation}")
    private Thread thread;

    public UserWatchedThread() {
    }

    public UserWatchedThread(User user, Thread thread) {
        // Sets fields
        this.user = user;
        this.thread = thread;

        // Sets identifier values
        this.id.userId = user.getId();
        this.id.threadId = thread.getId();

        // Guarantees referential integrity if made bidirectional
        user.getUserWatchedThreads().add(this);
        thread.getUserWatchedThreads().add(this);
    }

    public Id getId() {
        return id;
    }

    public Date getAddedOnDate() {
        return addedOnDate;
    }

    public void setAddedOnDate(Date addedOnDate) {
        this.addedOnDate = addedOnDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserWatchedThread that = (UserWatchedThread) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (addedOnDate != null ? !addedOnDate.equals(that.addedOnDate) : that.addedOnDate != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        return thread != null ? thread.equals(that.thread) : that.thread == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (addedOnDate != null ? addedOnDate.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (thread != null ? thread.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserWatchedThread{" +
                "id=" + id +
                ", addedOnDate=" + addedOnDate +
                ", user=" + user +
                ", thread=" + thread +
                '}';
    }
}
