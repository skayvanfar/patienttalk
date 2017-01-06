package ir.sk.patienttalk.persist.dao.hibernateImpl;


import ir.sk.patienttalk.common.persistence.jpa.hibernateImpl.HibernateGenericDAO;
import ir.sk.patienttalk.model.domain.User;
import ir.sk.patienttalk.persist.dao.UserDAO;
import org.springframework.stereotype.Repository;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/6/2017.
 */
@Repository
public class HibernateUserDAO extends HibernateGenericDAO<User, Long> implements UserDAO {

    public HibernateUserDAO() {
        super(User.class);
    }
}
