package ir.sk.patienttalk.persist.dao.hibernateImpl;

import ir.sk.patienttalk.common.persistence.jpa.hibernateImpl.HibernateGenericDAO;
import ir.sk.patienttalk.model.domain.Channel;
import ir.sk.patienttalk.persist.dao.ChannelDAO;
import org.springframework.stereotype.Repository;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/12/2017.
 */
@Repository
public class HibernateChannelDAO extends HibernateGenericDAO<Channel, Long> implements ChannelDAO {

    public HibernateChannelDAO() {
        super(Channel.class);
    }
}
