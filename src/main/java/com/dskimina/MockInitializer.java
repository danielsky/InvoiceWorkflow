package com.dskimina;

import com.dskimina.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MockInitializer {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void init(){
        userService.createUser("Daniel", "Skimina", "daniel@skimina.pl", "1234");
    }
}
