package ir.sk.patienttalk.model.domain;

import ir.sk.patienttalk.common.model.Value;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/9/2017.
 */
@Value
@Embeddable
public class EmailAddress {

    @Column(name = "MAIL", length = 100)
    @Basic
    @Size(min = 5, max = 100, message="{Size.EmailAddress.mail.validation}")
    private String mail;

    public EmailAddress() {
    }

    public EmailAddress(String mail) {
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmailAddress that = (EmailAddress) o;

        return mail != null ? mail.equals(that.mail) : that.mail == null;

    }

    @Override
    public int hashCode() {
        return mail != null ? mail.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "EmailAddress{" +
                "mail='" + mail + '\'' +
                '}';
    }
}
