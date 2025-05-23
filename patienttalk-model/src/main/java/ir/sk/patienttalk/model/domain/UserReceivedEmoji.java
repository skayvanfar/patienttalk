package ir.sk.patienttalk.model.domain;

import org.hibernate.annotations.Immutable;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/6/2017.
 */
@Entity
@Table(name = "TBL_PT_USER_RECEIVED_EMOJI", schema = "", catalog = "PATIENTTALK")
@Immutable // Declares class immutable
public class UserReceivedEmoji implements Serializable {

    // Encapsulates composite key
    @Embeddable
    public static class Id implements Serializable {

        @Column(name = "FK_USER_ID")
        protected Long userId;

        @Column(name = "FK_EMOJI_ID")
        protected Long emojiId;

        public Id() {
        }

        public Id(Long userId, Long emojiId) {
            this.userId = userId;
            this.emojiId = emojiId;
        }

        public boolean equals(Object o) {
            if (o != null && o instanceof Id) {
                Id that = (Id) o;
                return this.userId.equals(that.userId)
                        && this.emojiId.equals(that.emojiId);
            }
            return false;
        }

        public int hashCode() {
            return userId.hashCode() + emojiId.hashCode();
        }
    }

    // Maps identifier property and composite key columns
    @EmbeddedId
    protected Id id = new Id();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_ADDED_BY_USER_ID", nullable = false, updatable = false)
    @NotNull(message= "{NotNull.UserReceivedEmoji.addedByUser.validation}")
    protected User addedByUser;

    @Column(name = "ADDED_ON_DATE", nullable = false, updatable = false)
    @Basic
    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    protected Date addedOnDate = new Date();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_USER_ID", nullable = false, insertable = false, updatable = false)
    @NotNull(message= "{NotNull.UserReceivedEmoji.user.validation}")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_EMOJI_ID", nullable = false, insertable = false, updatable = false)
    @NotNull(message= "{NotNull.UserReceivedEmoji.emoji.validation}")
    private Emoji emoji;

    public UserReceivedEmoji() {
    }
    public UserReceivedEmoji(User addedByUser, User user, Emoji emoji) {
        // Sets fields
        this.addedByUser = addedByUser;
        this.user = user;
        this.emoji = emoji;

        // Sets identifier values
        this.id.userId = user.getId();
        this.id.emojiId = emoji.getId();

        // Guarantees referential integrity if made bidirectional
        user.getUserReceivedEmojis().add(this);
        emoji.getUserReceivedEmojis().add(this);
    }

    public Id getId() {
        return id;
    }

    public User getAddedByUser() {
        return addedByUser;
    }

    public void setAddedByUser(User addedByUser) {
        this.addedByUser = addedByUser;
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

    public Emoji getEmoji() {
        return emoji;
    }

    public void setEmoji(Emoji emoji) {
        this.emoji = emoji;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserReceivedEmoji that = (UserReceivedEmoji) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (addedByUser != null ? !addedByUser.equals(that.addedByUser) : that.addedByUser != null) return false;
        if (addedOnDate != null ? !addedOnDate.equals(that.addedOnDate) : that.addedOnDate != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        return emoji != null ? emoji.equals(that.emoji) : that.emoji == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (addedByUser != null ? addedByUser.hashCode() : 0);
        result = 31 * result + (addedOnDate != null ? addedOnDate.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (emoji != null ? emoji.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserReceivedEmoji{" +
                "id=" + id +
                ", addedByUser=" + addedByUser +
                ", addedOnDate=" + addedOnDate +
                ", user=" + user +
                ", emoji=" + emoji +
                '}';
    }
}
