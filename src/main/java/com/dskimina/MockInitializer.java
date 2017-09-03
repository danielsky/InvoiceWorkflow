package com.dskimina;

import com.dskimina.enums.Role;
import com.dskimina.exceptions.ObjectNotFoundException;
import com.dskimina.forms.ContractorForm;
import com.dskimina.forms.ServiceRequestForm;
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
    public void init() throws ObjectNotFoundException{
        businessLogic.createUser("Daniel", "Skimina", "daniel@skimina.pl", "1234", Role.EMPLOYEE);
        businessLogic.createUser("Dominic", "Smith", "dominic@smith.pl", "1234", Role.APPROVER);

        ServiceRequestForm form1 = new ServiceRequestForm();
        form1.setName("test1");
        form1.setContractor("Microsoft");
        form1.setPrice(125);
        form1.setCurrency("PLN");
        form1.setLocation("E00-543");

        ServiceRequestForm form2 = new ServiceRequestForm();
        form2.setName("test2");
        form2.setContractor("Google");
        form2.setPrice(250);
        form2.setCurrency("EUR");
        form2.setLocation("E00-544");

        businessLogic.createServiceRequest(form1, "daniel@skimina.pl");
        businessLogic.createServiceRequest(form2, "daniel@skimina.pl");

        ContractorForm contractor1 = new ContractorForm();
        contractor1.setName("Comarch");
        contractor1.setEmail("info@comarch.com");
        contractor1.setAddress("Kraków");
        businessLogic.createContractor(contractor1, "daniel@skimina.pl");

        ContractorForm contractor2 = new ContractorForm();
        contractor2.setName("Assecco");
        contractor2.setEmail("info@assecco.com");
        contractor2.setAddress("Rzeszów");
        businessLogic.createContractor(contractor2, "daniel@skimina.pl");

        ContractorForm contractor3 = new ContractorForm();
        contractor3.setName("Google");
        contractor3.setEmail("info@google.com");
        contractor3.setAddress("Mountain View");
        businessLogic.createContractor(contractor3, "daniel@skimina.pl");

        ContractorForm contractor4 = new ContractorForm();
        contractor4.setName("Apple");
        contractor4.setEmail("info@apple.com");
        contractor4.setAddress("Cupertino");
        businessLogic.createContractor(contractor4, "daniel@skimina.pl");

        ContractorForm contractor5 = new ContractorForm();
        contractor5.setName("Microsoft");
        contractor5.setEmail("info@microsoft.com");
        contractor5.setAddress("Redmont");
        businessLogic.createContractor(contractor5, "daniel@skimina.pl");
    }
}
