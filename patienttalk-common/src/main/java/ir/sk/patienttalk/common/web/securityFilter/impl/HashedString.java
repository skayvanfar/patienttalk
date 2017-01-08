package ir.sk.patienttalk.common.web.securityFilter.impl;

import ir.sk.patienttalk.common.web.securityFilter.Hashed;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/7/2017
 */
public class HashedString extends Hashed<String> {

    @Override
    protected String encrypt(String value) {
        return "hashed" + value;
    }

    @Override
    protected String decrypt(String value) {
        return value.substring(5);
    }

}
