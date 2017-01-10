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

    @Size(min = 5, max = 40, message="{Size.Trophy.name.validation}")
    @Column(name = "NAME", nullable = false, length = 40)
    @Basic
    @NotNull(message= "{NotNull.Trophy.name.validation}")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_USER_ID", nullable = false)
    @NotNull(message= "{NotNull.Trophy.user.validation}")
    private User user;

    public Trophy() {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Trophy trophy = (Trophy) o;

        if (id != trophy.id) return false;
        if (name != null ? !name.equals(trophy.name) : trophy.name != null) return false;
        return user != null ? user.equals(trophy.user) : trophy.user == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Trophy{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", user=" + user +
                '}';
    }
}
