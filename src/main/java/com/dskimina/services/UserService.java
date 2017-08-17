package com.dskimina.services;

import com.dskimina.enums.Role;
import com.dskimina.data.User;
import com.dskimina.exceptions.UserNotFoundException;
import com.dskimina.repositories.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private static final Log LOG = LogFactory.getLog(AuthenticationService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getByEmail(String email){
        return userRepository.getByEmail(email);
    }

    public void createUser(String name, String surname, String email, String password, Role role){
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPasswordHash(passwordEncoder.encode(password));
        user.setRole(role);
        userRepository.save(user);
        LOG.info("User created");
    }

    public User getApprover(){
        List<User> approverList = userRepository.findByRole(Role.APPROVER);
        if(approverList.isEmpty()){
            throw new UserNotFoundException("There is no approvers");
        }

        return approverList.get(0);
    }

}
