package ir.sk.patienttalk.webapp.service.impl;

import ir.sk.patienttalk.common.persistence.PersistenceException;
import ir.sk.patienttalk.common.service.impl.GenericManagerImpl;
import ir.sk.patienttalk.model.domain.Channel;
import ir.sk.patienttalk.persist.dao.ChannelDAO;
import ir.sk.patienttalk.webapp.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/12/2017.
 */
@Service
@Transactional(rollbackFor = PersistenceException.class)
public class ChannelServiceImpl extends GenericManagerImpl<Channel, Long> implements ChannelService {

    @Autowired
    private ChannelDAO channelDAO;

    @Autowired
    public ChannelServiceImpl(ChannelDAO channelDAO) {
        super(channelDAO);
        this.channelDAO = channelDAO;
    }

    @Override
    public Channel getChannel() throws PersistenceException {
        return channelDAO.get(1L); // TODO: 1/12/2017 just for now (test)
    }
}
