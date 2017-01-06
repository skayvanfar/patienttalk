package ir.sk.patienttalk.webapp.security;


import ir.sk.patienttalk.common.persistence.PersistenceException;
import ir.sk.patienttalk.model.domain.User;
import ir.sk.patienttalk.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/6/2017.
 */
@Service("userDetailsService")
public class JCGUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException, DataAccessException {
        try {
            User user = userService.getObjectByPropertyEqualTo("username", username);
            if (user == null)
                throw new UsernameNotFoundException("error.database.not.found");

            return new JCGUserDetails(user);
        } catch (PersistenceException e) {
            throw new UsernameNotFoundException(e.getType().getCode());
        }
    }
}
