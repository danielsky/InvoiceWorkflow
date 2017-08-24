package com.dskimina;

import com.dskimina.enums.Role;
import com.dskimina.forms.InvoiceForm;
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

        InvoiceForm form1 = new InvoiceForm();
        form1.setName("test1");
        form1.setSendNow(false);

        InvoiceForm form2 = new InvoiceForm();
        form2.setName("test2");
        form2.setSendNow(false);

        businessLogic.createInvoice(form1, "daniel@skimina.pl");
        businessLogic.createInvoice(form2, "daniel@skimina.pl");
    }
}
