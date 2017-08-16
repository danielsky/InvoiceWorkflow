package com.dskimina.services;

import com.dskimina.data.User;
import domain.CustomUserDetails;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    private static final Log LOG = LogFactory.getLog(AuthenticationService.class);

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        LOG.info(email);

        User user = userService.getByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("User with email "+ email+" not found");
        }
        return new CustomUserDetails(user);
    }
}
