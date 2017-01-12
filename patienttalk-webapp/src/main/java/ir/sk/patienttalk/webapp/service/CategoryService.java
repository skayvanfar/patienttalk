package ir.sk.patienttalk.webapp.service;

import ir.sk.patienttalk.common.persistence.PersistenceException;
import ir.sk.patienttalk.common.service.GenericManager;
import ir.sk.patienttalk.model.domain.Category;

import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/11/2017.
 */
public interface CategoryService extends GenericManager<Category, Long> {

    List<Category> getMainCategories(boolean fetchChildren) throws PersistenceException;

    void refreshChildren(Category category) throws PersistenceException;
}
