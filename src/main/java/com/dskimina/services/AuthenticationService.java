package com.dskimina.services;

import com.dskimina.CustomUserDetails;
import com.dskimina.data.User;
import com.dskimina.exceptions.ObjectNotFoundException;
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

        User user;
        try {
            user = userService.getByEmail(email);
        } catch (ObjectNotFoundException e) {
            throw new UsernameNotFoundException("Cannot find username", e);
        }

        return new CustomUserDetails(user);
    }
}
