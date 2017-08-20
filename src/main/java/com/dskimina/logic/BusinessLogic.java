package com.dskimina.logic;

import com.dskimina.enums.Role;
import com.dskimina.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BusinessLogic {

    @Autowired
    private UserService userService;

    @Transactional
    public void testTransactionEx(){
        for(int i=1;i<10;i++) {
            String name = "dan"+i;
            String surname = "sky"+i;
            String email = "DanSky"+i+"@gmail.com";
            userService.createUser(name, surname, email, "1234", Role.CONTRACTOR);
        }
        throw new IllegalStateException("test");
    }

    @Transactional
    public void testTransaction(){
        for(int i=1;i<10;i++) {
            String name = "dan"+i;
            String surname = "sky"+i;
            String email = "DanSky"+i+"@gmail.com";
            userService.createUser(name, surname, email, "1234", Role.CONTRACTOR);
        }
    }
}
