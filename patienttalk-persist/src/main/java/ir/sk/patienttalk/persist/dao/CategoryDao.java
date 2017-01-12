package ir.sk.patienttalk.persist.dao;

import ir.sk.patienttalk.common.persistence.PersistenceException;
import ir.sk.patienttalk.common.persistence.jpa.GenericDAO;
import ir.sk.patienttalk.model.domain.Category;

import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/11/2017.
 */
public interface CategoryDao extends GenericDAO<Category, Long> {

    List<Category> getMainCategories(boolean fetchChildren) throws PersistenceException;

    void refreshChildren(Category category) throws PersistenceException;

    List<Category> getChildCategories(long parentId) throws PersistenceException;
}
