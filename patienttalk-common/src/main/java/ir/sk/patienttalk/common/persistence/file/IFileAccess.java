package ir.sk.patienttalk.common.persistence.file;

import ir.sk.patienttalk.common.persistence.PersistenceException;
import ir.sk.patienttalk.common.persistence.jpa.EntityBase;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/9/2017.
 */
public interface IFileAccess {
    void clear(EntityBase entity) throws PersistenceException;
}
