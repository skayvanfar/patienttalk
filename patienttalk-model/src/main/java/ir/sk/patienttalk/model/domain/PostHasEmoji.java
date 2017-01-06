package ir.sk.patienttalk.model.domain;

import ir.sk.patienttalk.common.persistence.jpa.EntityBase;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/6/2017.
 */
@Entity
@Table(name = "TBL_PT_POST_HAS_EMOJI", schema = "", catalog = "PATIENTTALK")
public class PostHasEmoji extends EntityBase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PK_POST_HAS_EMOJI_ID", unique = true, nullable = false)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_POST_ID", nullable = false)
    @NotNull
    private Post post;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_EMOJI_ID", nullable = false)
    @NotNull
    private Emoji emoji;

    public PostHasEmoji() {
    }

    public long getId() {
        return id;
    }
}
