package ir.sk.patienttalk.webapp.controller;

import ir.sk.patienttalk.common.persistence.PersistenceException;
import ir.sk.patienttalk.model.domain.Category;
import ir.sk.patienttalk.webapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Random;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/7/2017
 */
public class BaseController {

    @Autowired
    private CategoryService categoryService;

    Random random = new Random();

    @ModelAttribute("categories")
    public List<Category> getCategories() throws PersistenceException {
        List<Category> mainCategories = categoryService.getMainCategories(true);
        return mainCategories;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST,  reason="Illegal request, please verify your payload")
    public void handleClientErrors(Exception ex) { }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason="Internal server error")
    public void handleServerErrors(Exception ex) {	}
}
