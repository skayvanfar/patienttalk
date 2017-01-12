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
@Table(name = "TBL_PT_SUBFORM", schema = "", catalog = "PATIENTTALK")
public class SubForum extends EntityBase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PK_SUBFORM_ID", unique = true, nullable = false)
    private long id;

    @Size(min = 5, max = 40, message="{Size.SubForum.name.validation}")
    @Column(name = "NAME", nullable = false, length = 40)
    @Basic
    @NotNull(message= "{NotNull.SubForum.name.validation}")
    private String name;

    @OneToMany(mappedBy = "subForum", cascade = {CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<Thread> threads = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_FORUM_ID", nullable = false)
    @NotNull(message= "{NotNull.SubForum.forum.validation}")
    private Forum forum;

    public SubForum() {
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

    public List<Thread> getThreads() {
        return threads;
    }

    public void setThreads(List<Thread> threads) {
        this.threads = threads;
    }

    public Forum getForum() {
        return forum;
    }

    public void setForum(Forum forum) {
        this.forum = forum;
    }

    // A convenience method simplifies relationship management
    public void addThread(Thread thread) {
        if (thread == null)
            throw new NullPointerException("Can't add null Thread"); // Be defensive
        if (thread.getSubForum() != null)
            throw new IllegalStateException("Thread is already assigned to an SubForum");
        getThreads().add(thread);
        thread.setSubForum(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubForum subForum = (SubForum) o;

        if (id != subForum.id) return false;
        if (name != null ? !name.equals(subForum.name) : subForum.name != null) return false;
        if (threads != null ? !threads.equals(subForum.threads) : subForum.threads != null) return false;
        return forum != null ? forum.equals(subForum.forum) : subForum.forum == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (threads != null ? threads.hashCode() : 0);
        result = 31 * result + (forum != null ? forum.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SubForum{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", threads=" + threads +
                ", forum=" + forum +
                '}';
    }
}
