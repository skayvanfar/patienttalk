package ir.sk.patienttalk.webapp.service.impl;

import ir.sk.patienttalk.common.persistence.PersistenceException;
import ir.sk.patienttalk.common.service.impl.GenericManagerImpl;
import ir.sk.patienttalk.model.domain.Forum;
import ir.sk.patienttalk.persist.dao.ForumDAO;
import ir.sk.patienttalk.webapp.service.ForumServce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/13/2017.
 */
@Service
@Transactional(rollbackFor = PersistenceException.class)
public class ForumServceImpl extends GenericManagerImpl<Forum, Long> implements ForumServce {

    @Autowired
    private ForumDAO forumDAO;

    @Autowired
    public ForumServceImpl(ForumDAO forumDAO) {
        super(forumDAO);
        this.forumDAO = forumDAO;
    }
}
