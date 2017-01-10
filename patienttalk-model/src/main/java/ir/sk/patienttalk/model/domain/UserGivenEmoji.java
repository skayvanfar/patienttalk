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
@Table(name = "TBL_PT_USER_GIVEN_EMOJI", schema = "", catalog = "PATIENTTALK")
@Immutable // Declares class immutable
public class UserGivenEmoji implements Serializable {

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
            if (o != null && o instanceof PostHasEmoji.Id) {
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
    @JoinColumn(name = "FK_RECEIVED_USER_ID", nullable = false, updatable = false)
    @NotNull(message= "{NotNull.UserGivenEmoji.receivedUser.validation}")
    protected User receivedUser;

    @Column(name = "ADDED_ON_DATE", nullable = false, updatable = false)
    @Basic
    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    protected Date addedOnDate = new Date();

    @ManyToOne
    @JoinColumn(name = "FK_USER_ID", nullable = false, insertable = false, updatable = false)
    @NotNull(message= "{NotNull.UserGivenEmoji.user.validation}")
    private User user;

    @ManyToOne
    @JoinColumn(name = "FK_EMOJI_ID", nullable = false, insertable = false, updatable = false)
    @NotNull(message= "{NotNull.UserGivenEmoji.emoji.validation}")
    private Emoji emoji;


    public UserGivenEmoji() {
    }

    public UserGivenEmoji(User receivedUser, User user, Emoji emoji) {
        // Sets fields
        this.receivedUser = receivedUser;
        this.user = user;
        this.emoji = emoji;

        // Sets identifier values
        this.id.userId = user.getId();
        this.id.emojiId = emoji.getId();

        // Guarantees referential integrity if made bidirectional
        user.getUserGivenEmojis().add(this);
        emoji.getUserGivenEmojis().add(this);
    }

    public Id getId() {
        return id;
    }

    public User getReceivedUser() {
        return receivedUser;
    }

    public void setReceivedUser(User receivedUser) {
        this.receivedUser = receivedUser;
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

        UserGivenEmoji that = (UserGivenEmoji) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (receivedUser != null ? !receivedUser.equals(that.receivedUser) : that.receivedUser != null) return false;
        if (addedOnDate != null ? !addedOnDate.equals(that.addedOnDate) : that.addedOnDate != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        return emoji != null ? emoji.equals(that.emoji) : that.emoji == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (receivedUser != null ? receivedUser.hashCode() : 0);
        result = 31 * result + (addedOnDate != null ? addedOnDate.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (emoji != null ? emoji.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserGivenEmoji{" +
                "id=" + id +
                ", receivedUser=" + receivedUser +
                ", addedOnDate=" + addedOnDate +
                ", user=" + user +
                ", emoji=" + emoji +
                '}';
    }
}
