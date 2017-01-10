package ir.sk.patienttalk.webapp.service.impl;

import ir.sk.patienttalk.common.persistence.PersistenceException;
import ir.sk.patienttalk.common.persistence.file.web.NamedInput;
import ir.sk.patienttalk.common.persistence.file.web.TempFileAccess;
import ir.sk.patienttalk.common.service.impl.GenericManagerImpl;
import ir.sk.patienttalk.model.domain.User;
import ir.sk.patienttalk.persist.dao.UserDAO;
import ir.sk.patienttalk.webapp.dto.SignupData;
import ir.sk.patienttalk.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.io.InputStream;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/6/2017.
 */
@Service
@Transactional
public class UserServiceImpl extends GenericManagerImpl<User, Long> implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    TempFileAccess tempFileAccess;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        super(userDAO);
        this.userDAO = userDAO;
    }

    // SignupLogic
    public void signup(SignupData data) throws PersistenceException {
        final User user = data.fill(null);

        userDAO.saveOrUpdate(user);
        if (data.getProfile().getPicture() != null)
            tempFileAccess.move(input -> user.getProfile().setPicture(new NamedInput(input, data.getProfile().getPicture())), data.getProfile().getPicture());
        data.setSucceed(true);
    }
}
