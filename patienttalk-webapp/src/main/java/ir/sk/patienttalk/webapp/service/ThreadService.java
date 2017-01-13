package ir.sk.patienttalk.webapp.service;

import ir.sk.patienttalk.common.persistence.PersistenceException;
import ir.sk.patienttalk.common.persistence.jpa.PagingDataList;
import ir.sk.patienttalk.common.service.GenericManager;
import ir.sk.patienttalk.model.domain.Thread;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/13/2017.
 */
public interface ThreadService extends GenericManager<Thread, Long> {
    PagingDataList<Thread> searchThreadsByPaging(String query, Integer page, int pageSize) throws PersistenceException;
}
