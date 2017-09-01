package com.dskimina;

import com.dskimina.enums.Role;
import com.dskimina.forms.InvoiceForm;
import com.dskimina.logic.BusinessLogic;
import com.dskimina.model.ContractorDTO;
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

        ContractorDTO contractor1 = new ContractorDTO();
        contractor1.setName("Comarch");
        contractor1.setEmail("info@comarch.com");
        contractor1.setAddress("Kraków");
        businessLogic.createContractor(contractor1, "daniel@skimina.pl");

        ContractorDTO contractor2 = new ContractorDTO();
        contractor2.setName("Assecco");
        contractor2.setEmail("info@assecco.com");
        contractor2.setAddress("Rzeszów");
        businessLogic.createContractor(contractor2, "daniel@skimina.pl");

        ContractorDTO contractor3 = new ContractorDTO();
        contractor3.setName("Google");
        contractor3.setEmail("info@google.com");
        contractor3.setAddress("Mountain View");
        businessLogic.createContractor(contractor3, "daniel@skimina.pl");

        ContractorDTO contractor4 = new ContractorDTO();
        contractor4.setName("Apple");
        contractor4.setEmail("info@apple.com");
        contractor4.setAddress("Cupertino");
        businessLogic.createContractor(contractor4, "daniel@skimina.pl");

        ContractorDTO contractor5 = new ContractorDTO();
        contractor5.setName("Microsoft");
        contractor5.setEmail("info@microsoft.com");
        contractor5.setAddress("Redmont");
        businessLogic.createContractor(contractor5, "daniel@skimina.pl");
    }
}
