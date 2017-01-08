package ir.sk.patienttalk.common.web.securityFilter.impl;

import ir.sk.patienttalk.common.web.securityFilter.HashedConverter;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/7/2017
 */
public class HashedLongConverter extends HashedConverter<HashedLong> {
    public HashedLongConverter() {
        super(HashedLong.class);
    }
}
