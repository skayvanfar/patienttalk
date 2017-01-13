package ir.sk.patienttalk.persist.dao.hibernateImpl;

import ir.sk.patienttalk.common.persistence.PersistenceException;
import ir.sk.patienttalk.common.persistence.jpa.PagingDataList;
import ir.sk.patienttalk.common.persistence.jpa.hibernateImpl.HibernateGenericDAO;
import ir.sk.patienttalk.model.domain.Thread;
import ir.sk.patienttalk.persist.dao.ThreadDAO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/13/2017.
 */
@Repository
public class HibernateThreadDAO extends HibernateGenericDAO<Thread, Long> implements ThreadDAO {

    public HibernateThreadDAO() {
        super(Thread.class);
    }

    @Override
    public PagingDataList<Thread> searchThreadsByPaging(String query, Integer page, int pageSize) throws PersistenceException {
        Criteria criteria = getSession().createCriteria(Thread.class);
        if (query != null) {
            String search = convertToSearch(query, true);
            criteria.add(Restrictions.like("name", search));
        }
        return paging(criteria, page, pageSize);
    }
}
