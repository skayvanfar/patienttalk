package ir.sk.patienttalk.patienttalkmodel.domain;

import ir.sk.patienttalk.patienttalkcommon.persistence.jpa.EntityBase;
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

    @OneToMany(mappedBy = "post", cascade = {CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<Emoji> emojis;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_THREAD_ID", nullable = false)
    @NotNull
    private Thread thread;

    public long getId() {
        return id;
    }
}
