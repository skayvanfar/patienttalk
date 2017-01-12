package ir.sk.patienttalk.webapp.service;

import ir.sk.patienttalk.common.persistence.PersistenceException;
import ir.sk.patienttalk.common.service.GenericManager;
import ir.sk.patienttalk.model.domain.Channel;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/12/2017.
 */
public interface ChannelService extends GenericManager<Channel, Long> {
    Channel getChannel() throws PersistenceException;
}
