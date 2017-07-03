package id.rojak.auth.service.security;

import id.rojak.auth.domain.User;
import id.rojak.auth.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by imrenagi on 5/8/17.
 */
@Service
public class MysqlUserDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(MysqlUserDetailsService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);
        log.info("user is found! -> " + user.getUsername());

        if (user == null) {
            log.info("user is not found!");
            throw new UsernameNotFoundException(s);
        }
        return user;
    }
}
