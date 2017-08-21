package com.dskimina;

import com.dskimina.enums.Role;
import com.dskimina.enums.WorkflowStep;
import com.dskimina.logic.BusinessLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Profile("dev")
public class MockInitializer {

    @Autowired
    private BusinessLogic businessLogic;

    @PostConstruct
    public void init(){
        businessLogic.createUser("Daniel", "Skimina", "daniel@skimina.pl", "1234", Role.EMPLOYEE);
        businessLogic.createUser("Dominic", "Smith", "dominic@smith.pl", "1234", Role.APPROVER);

        businessLogic.createInvoice("test1", "daniel@skimina.pl", WorkflowStep.CREATED);
        businessLogic.createInvoice("test2", "daniel@skimina.pl", WorkflowStep.WAITING_FOR_FIRST_APPROVE);
    }
}
