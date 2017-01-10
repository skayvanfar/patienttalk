package ir.sk.patienttalk.common.persistence.jpa;

import ir.sk.patienttalk.common.persistence.PersistenceException;
import ir.sk.patienttalk.common.persistence.PersistenceExceptionType;
import ir.sk.patienttalk.common.util.Utils;
import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

/**
 * DataAccessBase (Data Access Object) with common methods
 * that Daoes extend from this class.
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/10/2017.
 */
public abstract class DataAccessBase {

    @Autowired
    private SessionFactory sessionFactory;

    protected DataAccessBase() {
    }

    protected DataAccessBase(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Check that entity has invalid property.
     *
     * @param entities
     * @exception ir.sk.patienttalk.common.persistence.PersistenceException
     */
    protected static void checkIsValid(EntityBase... entities)
            throws PersistenceException {
        if (entities == null || entities.length == 0) {
            throw new PersistenceException(PersistenceExceptionType.BadParameter);
        }
        for (EntityBase e : entities) {
            String args = e.getInValidProperty();
            if (args != null) {
                throw new PersistenceException(PersistenceExceptionType.BadParameter, args);
            }
        }
    }

    /**
     * Check that entity has invalid property.
     *
     * @param entities
     * @exception ir.sk.patienttalk.common.persistence.PersistenceException
     */
    protected static void checkIsValid(List<EntityBase> entities)
            throws PersistenceException {
        if (entities == null || entities.size() == 0) {
            throw new PersistenceException(PersistenceExceptionType.BadParameter);
        }
        for (EntityBase e : entities) {
            String args = e.getInValidProperty();
            if (args != null) {
                throw new PersistenceException(PersistenceExceptionType.BadParameter, args);
            }
        }
    }

    protected static String standardArgs(String str, boolean canBeNull)
            throws PersistenceException {
        if (!Utils.isValid(str, canBeNull))
            throw new PersistenceException(PersistenceExceptionType.BadParameter);
        return Utils.convert2StandardString(str);
    }

    protected static String convertToSearch(String pattern,
                                            boolean replaceWhiteSpace) {
        if (pattern == null)
            return null;
        if (replaceWhiteSpace)
            pattern = pattern.trim().replaceAll("\\s+", "*");
        pattern = String.format("*%s*", pattern);
        pattern = pattern.replace('*', '%');
        pattern = pattern.replace('?', '_');
        pattern = pattern.replaceAll("%+", "%");
        return pattern;
    }

    protected void run(Transaction transaction, EntityBase... entities)
            throws PersistenceException {
        boolean[] isAt = new boolean[entities.length];
        for (int i = 0; i < entities.length; i++) {
            isAt[i] = s().contains(entities[i]);
            if (!isAt[i])
                s().replicate(entities[i], ReplicationMode.OVERWRITE);
        }
        transaction.run();
        for (int i = 0; i < entities.length; i++) {
            entities[i].toString();
            if (!isAt[i])
                s().evict(entities[i]);
        }
    }

    public abstract void write(Transaction transaction, EntityBase... entities)
            throws PersistenceException;

    public abstract void read(Transaction transaction, EntityBase... entities)
            throws PersistenceException;

    protected Session s() {
        return sessionFactory.getCurrentSession();
    }

    protected void setDataAccess(EntityBase... entities) {
    }

    @SuppressWarnings("rawtypes")
    protected void setDataAccess(List entities) {
    }

    protected void setDataAccess(Set entities) {
    }

    private void invisibleInit(EntityBase parent, final Object field)
            throws PersistenceException {
        synchronized (parent) {
            if (Hibernate.isInitialized(field))
                return;
            read(new Transaction() {
                @Override
                public void run() throws PersistenceException {
                    Hibernate.initialize(field);
                }
            }, parent);
        }
    }

    /**
     * Retrieve data with specified offset and limit
     *
     * @param criteria basic criteria that has entity
     * @param startPage start page
     * @param pageSize count of records in a page
     * @param orders array from order columns
     * */
    protected <T> PagingDataList<T> paging(Criteria criteria, int startPage,
                                           int pageSize, Order... orders) {
        if (orders != null)
            for (Order order : orders)
                criteria.addOrder(order);
        if (pageSize > 0) {
            //retain old projections. we are overwriting that in row count calculations.
            ResultTransformer oldTransformer = null;
            Projection oldProjection = null;
            if (criteria instanceof CriteriaImpl) {
                oldTransformer = ((CriteriaImpl) criteria)
                        .getResultTransformer();
                oldProjection = ((CriteriaImpl) criteria).getProjection();
            }
            Number rowCount = (Number) criteria.setProjection(
                    Projections.rowCount()).uniqueResult();
            if (startPage * pageSize > rowCount.intValue())
                startPage = rowCount.intValue() / pageSize;
            criteria.setProjection(null);
            if (oldTransformer != null)
                criteria.setResultTransformer(oldTransformer);
            if (oldProjection != null)
                criteria.setProjection(oldProjection);
            criteria.setFirstResult(startPage * pageSize);
            criteria.setMaxResults(pageSize);
            List<T> cs = (List<T>) criteria.list();
            setDataAccess(cs);
            return new PagingDataList<T>(cs, rowCount.intValue(), startPage,
                    pageSize);
        } else { // get all
            List<T> cs = (List<T>) criteria.list();
            return new PagingDataList<T>(cs, cs.size(), 0, cs.size());
        }
    }
}
