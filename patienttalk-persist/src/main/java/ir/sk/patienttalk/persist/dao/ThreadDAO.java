package ir.sk.patienttalk.persist.dao;

import ir.sk.patienttalk.common.persistence.PersistenceException;
import ir.sk.patienttalk.common.persistence.jpa.GenericDAO;
import ir.sk.patienttalk.common.persistence.jpa.PagingDataList;
import ir.sk.patienttalk.model.domain.Thread;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/13/2017.
 */
public interface ThreadDAO extends GenericDAO<Thread, Long> {
    PagingDataList<Thread> searchThreadsByPaging(String query, Integer page, int pageSize) throws PersistenceException;
}
