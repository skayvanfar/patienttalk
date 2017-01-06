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
    private long views;

    // How many time has replied
    private long replies;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_WATCHED_USER_ID", nullable = false)
    @NotNull
    private User watchedUser;

    public long getId() {
        return id;
    }
}
