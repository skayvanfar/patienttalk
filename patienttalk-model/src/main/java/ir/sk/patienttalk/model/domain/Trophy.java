package ir.sk.patienttalk.model.domain;

import ir.sk.patienttalk.common.persistence.jpa.EntityBase;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/5/2017.
 */
@Entity
@Table(name = "TBL_PT_TROPHY", schema = "", catalog = "PATIENTTALK")
public class Trophy extends EntityBase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PK_TROPHY_ID", unique = true, nullable = false)
    private long id;

    @Size(min = 5, max = 40)
    @Column(name = "NAME", nullable = false, insertable = true, updatable = true, length = 40)
    @Basic
    @NotNull
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_USER_ID", nullable = false)
    @NotNull
    private User user;

    public long getId() {
        return id;
    }

}
