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
public abstract class DataAccessBase extends PersistenceUtil {

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

}
