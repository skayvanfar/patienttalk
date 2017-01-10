package ir.sk.patienttalk.common.persistence.jpa;

import ir.sk.patienttalk.common.persistence.PersistenceException;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/10/2017.
 */
public interface Transaction {
    public void run() throws PersistenceException;
}
