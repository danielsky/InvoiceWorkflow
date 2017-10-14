package com.dskimina.services;

import com.dskimina.data.User;
import com.dskimina.enums.Role;
import com.dskimina.exceptions.ObjectNotFoundException;
import com.dskimina.repositories.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private static final Log LOG = LogFactory.getLog(AuthenticationService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getByEmail(String email) throws ObjectNotFoundException{
        User user = userRepository.getByEmail(email);
        if(user == null){
            throw new ObjectNotFoundException("Cannot find user by mail: "+ email);
        }
        return user;
    }

    public void createUser(String name, String surname, String email, String password, String role){
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPasswordHash(passwordEncoder.encode(password));
        user.setRole(role);
        userRepository.save(user);
        LOG.info("User created");
    }

    public User getApprover() throws ObjectNotFoundException{
        List<User> approverList = userRepository.findByRole(Role.APPROVER);
        if(approverList.isEmpty()){
            throw new ObjectNotFoundException("There is no approvers");
        }

        return approverList.get(0);
    }

    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        for(User user : userRepository.findAll()){
            users.add(user);
        }
        return users;
    }

}
