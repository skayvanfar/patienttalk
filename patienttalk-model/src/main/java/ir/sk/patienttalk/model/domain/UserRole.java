package ir.sk.patienttalk.model.domain;

import ir.sk.patienttalk.common.localization.LocalizedEnum;

import javax.persistence.*;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/5/2017.
 */
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
