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
@Table(name = "TBL_PT_CHANNEL", schema = "", catalog = "PATIENTTALK")
public class Channel extends EntityBase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PK_CHANNEL_ID", unique = true, nullable = false)
    private long id;

    @Size(min = 5, max = 40)
    @Column(name = "NAME", nullable = false, insertable = true, updatable = true, length = 40)
    @Basic
    @NotNull
    private String name;

    @OneToMany(mappedBy = "channel", cascade = {CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<Forum> forums;

    @Size(min = 5, max = 40)
    @Column(name = "THEME_COLOR", nullable = false, insertable = true, updatable = true, length = 40)
    @Basic
    @NotNull
    private String themeColor;

    public long getId() {
        return id;
    }
}
