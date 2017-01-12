package ir.sk.patienttalk.persist.dao.hibernateImpl;

import ir.sk.patienttalk.common.persistence.PersistenceException;
import ir.sk.patienttalk.common.persistence.jpa.hibernateImpl.HibernateGenericDAO;
import ir.sk.patienttalk.model.domain.Category;
import ir.sk.patienttalk.persist.dao.CategoryDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/11/2017.
 */
@Repository
public class HibernateCategoryDAO extends HibernateGenericDAO<Category, Long> implements CategoryDao {

    public HibernateCategoryDAO() {
        super(Category.class);
    }

    @Override
    public List<Category> getMainCategories(boolean fetchChildren) throws PersistenceException {
        Criteria criteria = getSession().createCriteria(Category.class).add(
                Restrictions.isNull("parentId"));
        List<Category> retValue = (List<Category>) criteria.list();
        if (fetchChildren) for (Category category : retValue)
            refreshChildren(category);
        return retValue;
    }

    @Override
    public void refreshChildren(Category category) throws PersistenceException {
        if (category.getChildren() != null) return;
        List<Category> children = getChildCategories(category.getId());
        if (!children.isEmpty())
            for (Category child : children)
                refreshChildren(child);
        category.setChildren(children);
    }

    @Override
    @Transactional(value = "db", propagation = Propagation.REQUIRED, rollbackFor = PersistenceException.class, readOnly = true)
    public List<Category> getChildCategories(long parentId) throws PersistenceException {
        Criteria criteria = getSession().createCriteria(Category.class).add(
                Restrictions.eq("parentId", parentId));
        return (List<Category>) criteria.list();
    }
}
