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
@Table(name = "TBL_PT_EMOJI", schema = "", catalog = "PATIENTTALK")
public class Emoji extends EntityBase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PK_EMOJI_ID", unique = true, nullable = false)
    private long id;

    @Size(min = 5, max = 40)
    @Column(name = "NAME", nullable = false, insertable = true, updatable = true, length = 40)
    @Basic
    @NotNull
    private String name;

    @OneToMany(mappedBy = "emoji", cascade = {CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<PostHasEmoji> postHasEmojis;

    @OneToMany(mappedBy = "emoji", cascade = {CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<UserGivenEmoji> userGivenEmojis;

    @OneToMany(mappedBy = "emoji", cascade = {CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<UserReceivedEmoji> userReceivedEmojis;

    public long getId() {
        return id;
    }
}
