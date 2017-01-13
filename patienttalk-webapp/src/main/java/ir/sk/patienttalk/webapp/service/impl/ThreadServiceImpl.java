package ir.sk.patienttalk.webapp.service.impl;

import ir.sk.patienttalk.common.persistence.PersistenceException;
import ir.sk.patienttalk.common.persistence.jpa.PagingDataList;
import ir.sk.patienttalk.common.service.impl.GenericManagerImpl;
import ir.sk.patienttalk.model.domain.Thread;
import ir.sk.patienttalk.persist.dao.ThreadDAO;
import ir.sk.patienttalk.webapp.service.ThreadService;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/13/2017.
 */
@Service
@Transactional(rollbackFor = PersistenceException.class)
public class ThreadServiceImpl extends GenericManagerImpl<Thread, Long> implements ThreadService {

    @Autowired
    private ThreadDAO threadDAO;

    @Autowired
    public ThreadServiceImpl(ThreadDAO threadDAO) {
        super(threadDAO);
        this.threadDAO = threadDAO;
    }

    @Override
    public PagingDataList<Thread> searchThreadsByPaging(String query, Integer page, int pageSize) throws PersistenceException {
        return threadDAO.searchThreadsByPaging(query, page, pageSize);
    }
}
