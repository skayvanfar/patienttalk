package ir.sk.patienttalk.webapp.service.impl;

import ir.sk.patienttalk.common.service.impl.GenericManagerImpl;
import ir.sk.patienttalk.model.domain.User;
import ir.sk.patienttalk.persist.dao.UserDAO;
import ir.sk.patienttalk.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/6/2017.
 */
@Service
public class UserServiceImpl extends GenericManagerImpl<User, Long> implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        super(userDAO);
        this.userDAO = userDAO;
    }
}
