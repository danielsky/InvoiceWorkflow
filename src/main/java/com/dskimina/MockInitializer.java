package com.dskimina;

import com.dskimina.enums.Role;
import com.dskimina.enums.WorkflowStep;
import com.dskimina.services.InvoiceService;
import com.dskimina.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Profile("dev")
public class MockInitializer {

    @Autowired
    private UserService userService;

    @Autowired
    private InvoiceService invoiceService;

    @PostConstruct
    public void init(){
        userService.createUser("Daniel", "Skimina", "daniel@skimina.pl", "1234", Role.EMPLOYEE);
        userService.createUser("Dominic", "Smith", "dominic@smith.pl", "1234", Role.APPROVER);

        invoiceService.createInvoice("test1", "daniel@skimina.pl", WorkflowStep.CREATED);
        invoiceService.createInvoice("test2", "daniel@skimina.pl", WorkflowStep.WAITING_FOR_FIRST_APPROVE);
    }
}
