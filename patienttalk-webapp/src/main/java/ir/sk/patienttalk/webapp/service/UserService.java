package ir.sk.patienttalk.webapp.service;

import ir.sk.patienttalk.common.persistence.PersistenceException;
import ir.sk.patienttalk.common.service.GenericManager;
import ir.sk.patienttalk.model.domain.User;
import ir.sk.patienttalk.webapp.dto.SignupData;
import org.springframework.validation.BindingResult;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/6/2017.
 */
public interface UserService extends GenericManager<User, Long> {
    void signup(SignupData data) throws PersistenceException;
}
