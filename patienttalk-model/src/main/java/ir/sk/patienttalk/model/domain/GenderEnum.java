package ir.sk.patienttalk.model.domain;

import ir.sk.patienttalk.common.localization.LocalizedEnum;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/5/2017.
 */
public enum GenderEnum implements LocalizedEnum {
    MALE, FEMALE, UNKNOWN;

    @Override
    public String getString() {
        return toString();
    }

    @Override
    public int getOrdinal() {
        return ordinal();
    }

    @Override
    public String getCode() {
        switch (this) {
            case MALE:
                return "مرد";
            case FEMALE:
                return "زن";
            case UNKNOWN:
                return "نامشخص";
        }
        return null;
    }
}
