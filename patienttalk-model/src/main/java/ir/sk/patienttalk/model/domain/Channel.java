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
@Table(name = "TBL_PT_CHANNEL", schema = "", catalog = "PATIENTTALK")
public class Channel extends EntityBase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PK_CHANNEL_ID", unique = true, nullable = false)
    private long id;

    @Size(min = 5, max = 40, message="{Size.Channel.name.validation}")
    @Column(name = "NAME", nullable = false, length = 40)
    @Basic
    @NotNull(message= "{NotNull.Channel.name.validation}")
    private String name;

    @OneToMany(mappedBy = "channel", cascade = {CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<Forum> forums;

    @Size(min = 5, max = 40, message="{Size.Channel.themeColor.validation}")
    @Column(name = "THEME_COLOR", nullable = false, length = 40)
    @Basic
    @NotNull(message= "{NotNull.Channel.themeColor.validation}")
    private String themeColor;

    public Channel() {
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

    public List<Forum> getForums() {
        return forums;
    }

    public void setForums(List<Forum> forums) {
        this.forums = forums;
    }

    public String getThemeColor() {
        return themeColor;
    }

    public void setThemeColor(String themeColor) {
        this.themeColor = themeColor;
    }

    // A convenience method simplifies relationship management
    public void addForum(Forum forum) {
        if (forum == null)
            throw new NullPointerException("Can't add null Forum"); // Be defensive
        if (forum.getChannel() != null)
            throw new IllegalStateException("Forum is already assigned to an Channel");
        getForums().add(forum);
        forum.setChannel(this);
    }
}
