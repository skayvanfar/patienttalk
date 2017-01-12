package ir.sk.patienttalk.webapp.service.impl;

import ir.sk.patienttalk.common.persistence.PersistenceException;
import ir.sk.patienttalk.common.service.impl.GenericManagerImpl;
import ir.sk.patienttalk.model.domain.Category;
import ir.sk.patienttalk.persist.dao.CategoryDao;
import ir.sk.patienttalk.webapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/11/2017.
 */
@Service
@Transactional(rollbackFor = PersistenceException.class)
public class CategoryServiceImpl extends GenericManagerImpl<Category, Long> implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    public CategoryServiceImpl(CategoryDao categoryDao) {
        super(categoryDao);
        this.categoryDao = categoryDao;
    }

    @Override
    //   @Method
    //  @Transactional(value = "db", propagation = Propagation.REQUIRED, rollbackFor = PersistenceException.class, readOnly = true)
    public List<Category> getMainCategories(boolean fetchChildren) throws PersistenceException {
        return categoryDao.getMainCategories(fetchChildren);
    }

    @Override
    // @Method
    @Transactional(value = "db", propagation = Propagation.REQUIRED, rollbackFor = PersistenceException.class, readOnly = true)
    public void refreshChildren(Category category) throws PersistenceException {
        categoryDao.refreshChildren(category);
    }
}
