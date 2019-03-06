package ir.sk.patienttalk.webapp.controller;

import ir.sk.patienttalk.common.persistence.PersistenceException;
import ir.sk.patienttalk.common.service.GenericManager;
import ir.sk.patienttalk.common.persistence.SearchData;
import ir.sk.patienttalk.webapp.dto.UserSearchData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;


import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Map;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 9/8/2017.
 */
public class GenericController<T, K extends SearchData, PK extends Serializable> {
    /**
     * Log variable for all child classes. Uses LogFactory.getLog(getClass()) from Commons Logging
     */
    private static final Logger logger = LoggerFactory.getLogger(GenericController.class);

    private Class<T> entityClass;

    /**
     * GenericDAO instance, set by constructor of child classes
     */
    protected GenericManager<T, PK> genericManager;

    /**
     * Constructor that takes in a class to see which type of entity to control.
     * Use this constructor when subclassing.
     */
    public GenericController(final Class<T> entityClass, GenericManager<T, PK> genericManager) {
        this.entityClass = entityClass;
        this.genericManager = genericManager;
    }

    @RequestMapping("/search")
    public String search(@ModelAttribute K searchData, Map<String, Object> model) throws PersistenceException {
        model.put(searchData.getClass().getSimpleName(), searchData);
        model.put(entityClass.getSimpleName().toLowerCase() + "s", genericManager.search(searchData));
        return entityClass.getSimpleName().toLowerCase() + "/search";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST,  reason="Illegal request, please verify your payload")
    public void handleClientErrors(Exception ex) { }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason="Internal server error")
    public void handleServerErrors(Exception ex) {	}

}
