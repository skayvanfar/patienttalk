package ir.sk.patienttalk.persist.dao.hibernateImpl;

import ir.sk.patienttalk.common.persistence.jpa.hibernateImpl.HibernateGenericDAO;
import ir.sk.patienttalk.model.domain.Forum;
import ir.sk.patienttalk.persist.dao.ForumDAO;
import org.springframework.stereotype.Repository;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/13/2017.
 */
@Repository
public class HibernateForumDAO extends HibernateGenericDAO<Forum, Long> implements ForumDAO {
    public HibernateForumDAO() {
        super(Forum.class);
    }
}
