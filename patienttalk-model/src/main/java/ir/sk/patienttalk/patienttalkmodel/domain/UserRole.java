package ir.sk.patienttalk.patienttalkmodel.domain;

import ir.sk.patienttalk.patienttalkcommon.localization.LocalizedEnum;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/5/2017.
 */
@Entity
@Table(name = "TBL_PT_USER_ROLE", schema = "", catalog = "PATIENTTALK")
public enum UserRole implements LocalizedEnum {

    SUPER_ADMIN, ADMIN, USER;

    public String getString() {
        return toString();
    }

    public int getOrdinal() {
        return ordinal();
    }

    public String getCode() {
        return toString();
    }
}
