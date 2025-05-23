package ir.sk.patienttalk.common.service.impl;

import ir.sk.patienttalk.common.persistence.PersistenceException;
import ir.sk.patienttalk.common.persistence.SearchData;
import ir.sk.patienttalk.common.persistence.jpa.GenericDAO;
import ir.sk.patienttalk.common.persistence.jpa.PagingDataList;
import ir.sk.patienttalk.common.service.GenericManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * This class serves as the Base class for all other Managers - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 * <p/>
 * <p>To register this class in your Spring context file, use the following XML.
 * <pre>
 *     &lt;bean id="userManager" class="net.luna.service.impl.GenericManagerImpl"&gt;
 *         &lt;constructor-arg&gt;
 *             &lt;bean class="net.luna.dao.hibernateImpl.HibernateGenericDAO"&gt;
 *                 &lt;constructor-arg value="net.luna.model.User"/&gt;
 *                 &lt;property name="sessionFactory" ref="sessionFactory"/&gt;
 *             &lt;/bean&gt;
 *         &lt;/constructor-arg&gt;
 *     &lt;/bean&gt;
 * </pre>
 * <p/>
 * <p>If you're using iBATIS instead of Hibernate, use:
 * <pre>
 *     &lt;bean id="userManager" class="net.luna.service.impl.GenericManagerImpl"&gt;
 *         &lt;constructor-arg&gt;
 *             &lt;bean class="net.luna.dao.ibatis.GenericDaoiBatis"&gt;
 *                 &lt;constructor-arg value="net.luna.model.User"/&gt;
 *                 &lt;property name="dataSource" ref="dataSource"/&gt;
 *                 &lt;property name="sqlMapClient" ref="sqlMapClient"/&gt;
 *             &lt;/bean&gt;
 *         &lt;/constructor-arg&gt;
 *     &lt;/bean&gt;
 * </pre>
 *
 * @param <T>  a type variable
 * @param <PK> the primary key for that type
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/6/2017.
 */
@Transactional
public class GenericManagerImpl<T, PK extends Serializable> implements GenericManager<T, PK> {

    /**
     * Log variable for all child classes. Uses LogFactory.getLog(getClass()) from Commons Logging
     */
    protected final Log log = LogFactory.getLog(getClass());

    /**
     * GenericDAO instance, set by constructor of child classes
     */
    protected GenericDAO<T, PK> dao;


    public GenericManagerImpl() {
    }

    public GenericManagerImpl(GenericDAO<T, PK> genericDAO) {
        this.dao = genericDAO;
    }

    /**
     * {@inheritDoc}
     */
    public T get(PK id) {
        return dao.get(id);
    }

    /**
     * {@inheritDoc}
     */
    public List<T> getAll() {
        return dao.getAll();
    }

    /**
     * {@inheritDoc}
     */
    public List<T> getAllDistinct() {
        return dao.getAllDistinct();
    }

    /**
     * {@inheritDoc}
     */
    public List<T> getByIds(String property, String[] ids) {
        return dao.getByIds(property, ids);
    }

    /**
     * {@inheritDoc}
     */
    public <V extends Object> T getObjectByPropertyEqualTo(String propertyName, V propertyValue) {
        return dao.getObjectByPropertyEqualTo(propertyName, propertyValue);
    }

    /**
     * {@inheritDoc}
     */
    public <V extends Object> List<T> getByPropertyEqualTo(String propertyName, V propertyValue) {
        return dao.getByPropertyEqualTo(propertyName, propertyValue);
    }

    /**
     * {@inheritDoc}
     */
    public <V extends Object> List<T> getByProperties(String propertyName, V[] propertyValue) {
        return dao.getByProperties(propertyName, propertyValue);
    }

    /**
     * {@inheritDoc}
     */
    public List<T> getByProperties(String propertyName, String[] propertyValue) {
        return dao.getByProperties(propertyName, propertyValue);
    }

    /**
     * {@inheritDoc}
     */
    public List<T> getByProperties(String[] propertyName, String[] propertyValue) {
        return dao.getByProperties(propertyName, propertyValue);
    }

    /**
     * {@inheritDoc}
     */
    public T getObjectByProperties(String[] propertyName, String[] propertyValue) {
        return dao.getObjectByProperties(propertyName, propertyValue);
    }
//

    /**
     * {@inheritDoc}
     */
    public <V> List<T> getByPropertiesLikeExact(String[] propertyName, V[] propertyValue) {
        return dao.getByPropertiesLikeExact(propertyName, propertyValue);
    }

    /**
     * {@inheritDoc}
     */
    public List<T> getByPropertiesLikeExact(String[] propertyName, String propertyValue) {
        return dao.getByPropertiesLikeExact(propertyName, propertyValue);
    }

    /**
     * {@inheritDoc}
     */
    public <V extends Object> boolean existByProperty(String propertyName, V propertyValue) {
        return dao.existByProperty(propertyName, propertyValue);
    }

    @Override
    public <V extends Object> List<T> getByAscOrder(String propertyForOrder) {
        return dao.getByAscOrder(propertyForOrder);
    }

    @Override
    public <V extends Object> List<T> getByDescOrder(String propertyForOrder) {
        return dao.getByDescOrder(propertyForOrder);
    }

    @Override
    public <V extends Object> List<T> getByPageAndRow(int page, int row) {
        return dao.getByPageAndRow(page, row);
    }
    //


    /**
     * {@inheritDoc}
     */
    public <V extends Object> List<T> getByPropertyOfPropertyEqualTo(String property, String propertyOfProperty, V value) {
        return dao.getByPropertyOfPropertyEqualTo(property, propertyOfProperty, value);
    }

    /**
     * {@inheritDoc}
     */
    public <V extends Object> T getObjectByPropertyOfPropertyEqualTo(String property, String propertyOfProperty, V value) {
        return dao.getObjectByPropertyOfPropertyEqualTo(property, propertyOfProperty, value);
    }

    /**
     * {@inheritDoc}
     */
    public <V extends Object> List<T> getByPropertyEqualToIgnoreCase(String propertyName, V propertyValue) {
        return dao.getByPropertyEqualToIgnoreCase(propertyName, propertyValue);
    }

    /**
     * {@inheritDoc}
     */
    public <V extends Object> List<T> getByPropertyLessThanOrEqualTo(String propertyName, V propertyValue) {
        return dao.getByPropertyLessThanOrEqualTo(propertyName, propertyValue);
    }

    /**
     * {@inheritDoc}
     */
    public <V extends Object> List<T> getByPropertyGreaterThanOrEqualTo(String propertyName, V propertyValue) {
        return dao.getByPropertyGreaterThanOrEqualTo(propertyName, propertyValue);
    }

    /**
     * {@inheritDoc}
     */
    public <V extends Object> List<T> getByPropertyLessThan(String propertyName, V propertyValue) {
        return dao.getByPropertyLessThan(propertyName, propertyValue);
    }

    /**
     * {@inheritDoc}
     */
    public <V extends Object> List<T> getByPropertyGreaterThan(String propertyName, V propertyValue) {
        return dao.getByPropertyGreaterThan(propertyName, propertyValue);
    }

    /**
     * {@inheritDoc}
     */
    public List<T> getByPropertyLikeAnyWhereMode(String propertyName, String propertyValue) {
        return dao.getByPropertyLikeAnyWhereMode(propertyName, propertyValue);
    }

    /**
     * {@inheritDoc}
     */
    public List<T> getByPropertyLikeAnyWhereModeIgnoreCase(String propertyName, String propertyValue) {
        return dao.getByPropertyLikeAnyWhereModeIgnoreCase(propertyName, propertyValue);
    }

    /**
     * {@inheritDoc}
     */
    public <V extends Object> T getObjectByPropertyEqualToIgnoreCase(String propertyName, V propertyValue) {
        return dao.getObjectByPropertyEqualToIgnoreCase(propertyName, propertyValue);
    }

    /**
     * {@inheritDoc}
     */
    public <V extends Object> List<T> getByProperties(String[] propertyName, V[] propertyValue) {
        return dao.getByProperties(propertyName, propertyValue);
    }

    /**
     * {@inheritDoc}
     */
    public <V extends Object> List<T> getByPropertiesWithDescOrder(String[] propertyName, V[] propertyValue, String propertyForOrder) {
        return dao.getByPropertiesWithDescOrder(propertyName, propertyValue, propertyForOrder);
    }

    /**
     * {@inheritDoc}
     */
    public <V extends Object> List<T> getByPropertiesWithAscOrder(String[] propertyName, V[] propertyValue, String propertyForOrder) {
        return dao.getByPropertiesWithAscOrder(propertyName, propertyValue, propertyForOrder);
    }

    /**
     * {@inheritDoc}
     */
    public <V extends Object> List<T> getByPropertyNotEqualTo(String propertyName, V propertyValue) {
        return dao.getByPropertyNotEqualTo(propertyName, propertyValue);
    }

    /**
     * {@inheritDoc}
     */
    public List<T> getByPropertyLikeExact(String propertyName, String propertyValue) {
        return dao.getByPropertyLikeExact(propertyName, propertyValue);
    }

    /**
     * {@inheritDoc}
     */
    public T getByPropertyLikeExactModeIgnoreCase(String propertyName, String propertyValue) {
        return dao.getByPropertyLikeExactModeIgnoreCase(propertyName, propertyValue);
    }

    /**
     * {@inheritDoc}
     */
    public T getByPropertyLikeModeIgnoreCase(String propertyName, String propertyValue){
        return dao.getByPropertyLikeModeIgnoreCase(propertyName, propertyValue);
    }

    /**
     * {@inheritDoc}
     */
    public List<T> getByPropertyLikeAnyWhereModeIgnoreCasePartially(String propertyName, String propertyValue) {
        return dao.getByPropertyLikeAnyWhereModeIgnoreCasePartially(propertyName, propertyValue);
    }

    /**
     * {@inheritDoc}
     */
    public List<T> getByAssociatedProperty(String associatedProperty, String propertyName, Integer propertyValue) {
        return dao.getByAssociatedProperty(associatedProperty, propertyName, propertyValue);
    }

    /**
     * {@inheritDoc}
     */
    public T save(T object) {
        return dao.save(object);
    }

    /**
     * {@inheritDoc}
     */
    public void reattachToSession(T object) {
        dao.reattachToSession(object);
    }

    /**
     * {@inheritDoc}
     */
    public void update(T object) {

    }

    /**
     * {@inheritDoc}
     */
    public void saveOrUpdate(T object) {
        dao.saveOrUpdate(object);
    }

//    void saveOrUpdateAll(Collection<T> coll);

    /**
     * {@inheritDoc}
     */
    public void remove(T object) {
        dao.remove(object);
    }

    /**
     * {@inheritDoc}
     */
    public void remove(PK id) {
        dao.remove(id);
    }

    /**
     * {@inheritDoc}
     */
    public void deleteAll() {
        dao.deleteAll();
    }

    /**
     * {@inheritDoc}
     */
    public List<T> findByNamedQuery(String queryName, Map<String, Object> queryParams) {
        return dao.findByNamedQuery(queryName, queryParams);
    }

    /**
     * {@inheritDoc}
     */
    public long count() {
        return dao.count();
    }

    /**
     * {@inheritDoc}
     */
    public boolean exists(PK id) {
        return dao.exists(id);
    }

    @Override
    public PagingDataList<T> search(SearchData searchData) throws PersistenceException {
        return dao.search(searchData);
    }
}
