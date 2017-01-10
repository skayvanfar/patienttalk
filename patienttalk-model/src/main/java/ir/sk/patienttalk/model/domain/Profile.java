package ir.sk.patienttalk.model.domain;

import ir.sk.patienttalk.common.model.Value;
import ir.sk.patienttalk.common.persistence.PersistenceException;
import ir.sk.patienttalk.common.persistence.file.web.FileAccess;
import ir.sk.patienttalk.common.persistence.file.web.FileItem;
import ir.sk.patienttalk.common.persistence.file.web.NamedInput;
import ir.sk.patienttalk.common.persistence.jpa.EntityBase;
import org.hibernate.annotations.Parent;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;

/**
 * Value Object
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/9/2017.
 */
@Value
@Embeddable
public class Profile implements Serializable {

    @Parent
    protected User user;

    @Column(name = "NAME", length = 50)
    @Basic
    @Size(min = 5, max = 50, message="{Size.Profile.name.message}")
    private String name;

    @Column(name = "FAMILY", length = 80)
    @Basic
    @Size(min = 2, max = 80, message="{Size.Profile.family.message}")
    private String family;

    @Column(name = "GENDER_ENUM", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private GenderEnum genderEnum = GenderEnum.UNKNOWN;

    @Column(name = "NICK_NAME", length = 100)
    @Basic
    @Size(min = 2, max = 100, message="{Size.Profile.nickName.message}")
    private String nickName;

    @Column(name = "NATIONALITY_ENUM")
    @Enumerated(EnumType.ORDINAL)
    private NationalityEnum nationalityEnum;

    @Column(name = "PHONE", length = 20)
    @Basic
    @Size(min = 4, max = 20, message="{Size.Profile.phone.message}")
    private String phone;

    @Size(max = 50, message="{Size.Profile.location.message}")
    @Column(name = "LOCATION", nullable = false, length = 50, unique = true)
    @Basic
    private String location;

    @Embedded
    private EmailAddress emailAddress;

    @Column(name = "CAUSE_OF_ILLNESS", length = 100)
    @Basic
    @Size(min = 5, max = 100, message="{Size.Profile.CauseOfIllness.message}")
    private String CauseOfIllness;

    @Column(name = "ILLNESS_SINCE", length = 100)
    @Basic
    @Size(min = 5, max = 100, message="{Size.Profile.illnessSince.message}")
    private String illnessSince;

    @Column(name = "MEMBER_DATE", nullable = false)
    @Basic
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date memberDate = new Date();

    @Column(name = "LAST_SEEN_DATE", nullable = false)
    @Basic
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date lastSeenDate = new Date();

    @Column(name = "LAST_ACTIVITY_DATE", nullable = false)
    @Basic
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date LastActivityDate = new Date();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public GenderEnum getGenderEnum() {
        return genderEnum;
    }

    public void setGenderEnum(GenderEnum genderEnum) {
        this.genderEnum = genderEnum;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public NationalityEnum getNationalityEnum() {
        return nationalityEnum;
    }

    public void setNationalityEnum(NationalityEnum nationalityEnum) {
        this.nationalityEnum = nationalityEnum;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public EmailAddress getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(EmailAddress emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getCauseOfIllness() {
        return CauseOfIllness;
    }

    public void setCauseOfIllness(String causeOfIllness) {
        CauseOfIllness = causeOfIllness;
    }

    public String getIllnessSince() {
        return illnessSince;
    }

    public void setIllnessSince(String illnessSince) {
        this.illnessSince = illnessSince;
    }

    public Date getMemberDate() {
        return memberDate;
    }

    public void setMemberDate(Date memberDate) {
        this.memberDate = memberDate;
    }

    public Date getLastSeenDate() {
        return lastSeenDate;
    }

    public void setLastSeenDate(Date lastSeenDate) {
        this.lastSeenDate = lastSeenDate;
    }

    public Date getLastActivityDate() {
        return LastActivityDate;
    }

    public void setLastActivityDate(Date lastActivityDate) {
        LastActivityDate = lastActivityDate;
    }

    public FileItem setPicture(NamedInput input) throws PersistenceException {
        if (input == null) {
            FileAccess.i().clear(user);
            return null;
        }
        return FileAccess.i().save(input, true, user, "picture");
    }

    public FileItem setPicture(InputStream fileInputStream) throws PersistenceException {
        if (fileInputStream == null) {
            FileAccess.i().clear(user);
            return null;
        }
        return FileAccess.i().save(new NamedInput(fileInputStream), true, user, "picture");
    }

    public FileItem getPicture() throws PersistenceException {
        return FileAccess.i().read(user, "picture");
    }

    public String getFullName() {
        if (nickName != null) return nickName;
        final StringBuilder sb = new StringBuilder();
        if (name != null)
            sb.append(name);
        sb.append(' ');
        if (family != null)
            sb.append(family);
        return sb.toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Profile profile = (Profile) o;

        if (user != null ? !user.equals(profile.user) : profile.user != null) return false;
        if (name != null ? !name.equals(profile.name) : profile.name != null) return false;
        if (family != null ? !family.equals(profile.family) : profile.family != null) return false;
        if (genderEnum != profile.genderEnum) return false;
        if (nickName != null ? !nickName.equals(profile.nickName) : profile.nickName != null) return false;
        if (nationalityEnum != profile.nationalityEnum) return false;
        if (phone != null ? !phone.equals(profile.phone) : profile.phone != null) return false;
        if (location != null ? !location.equals(profile.location) : profile.location != null) return false;
        if (emailAddress != null ? !emailAddress.equals(profile.emailAddress) : profile.emailAddress != null)
            return false;
        return CauseOfIllness != null ? CauseOfIllness.equals(profile.CauseOfIllness) : profile.CauseOfIllness == null;

    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (family != null ? family.hashCode() : 0);
        result = 31 * result + (genderEnum != null ? genderEnum.hashCode() : 0);
        result = 31 * result + (nickName != null ? nickName.hashCode() : 0);
        result = 31 * result + (nationalityEnum != null ? nationalityEnum.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (emailAddress != null ? emailAddress.hashCode() : 0);
        result = 31 * result + (CauseOfIllness != null ? CauseOfIllness.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "user=" + user +
                ", name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", genderEnum=" + genderEnum +
                ", nickName='" + nickName + '\'' +
                ", nationalityEnum=" + nationalityEnum +
                ", phone='" + phone + '\'' +
                ", location='" + location + '\'' +
                ", emailAddress=" + emailAddress +
                ", CauseOfIllness='" + CauseOfIllness + '\'' +
                '}';
    }
}
