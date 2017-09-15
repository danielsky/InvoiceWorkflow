package com.dskimina;

import com.dskimina.enums.Currency;
import com.dskimina.enums.Role;
import com.dskimina.exceptions.ObjectNotFoundException;
import com.dskimina.forms.CommentForm;
import com.dskimina.forms.ContractorForm;
import com.dskimina.forms.ServiceRequestForm;
import com.dskimina.logic.BusinessLogic;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Profile("dev")
public class MockInitializer {


    private static final Log LOG = LogFactory.getLog(MockInitializer.class);

    public static final String CREATOR_EMAIL = "daniel@skimina.pl";

    @Autowired
    private BusinessLogic businessLogic;

    @PostConstruct
    public void init() throws ObjectNotFoundException{
        businessLogic.createUser("Daniel", "Skimina", "daniel@skimina.pl", "1234", Role.EMPLOYEE);
        businessLogic.createUser("Dominic", "Smith", "dominic@asdf.pl", "1234", Role.APPROVER);
        businessLogic.createUser("Dorian", "Nowak", "dorian@asdf.pl", "1234", Role.EMPLOYEE);
        businessLogic.createUser("David", "Kowalski", "david@asdf.pl", "1234", Role.EMPLOYEE);

        ContractorForm contractor1 = new ContractorForm();
        contractor1.setName("Comarch");
        contractor1.setEmail("info@comarch.com");
        contractor1.setAddress("Kraków");
        String contractorId1 = businessLogic.createContractor(contractor1, CREATOR_EMAIL);

        ContractorForm contractor2 = new ContractorForm();
        contractor2.setName("Assecco");
        contractor2.setEmail("info@assecco.com");
        contractor2.setAddress("Rzeszów");
        businessLogic.createContractor(contractor2, CREATOR_EMAIL);

        ContractorForm contractor3 = new ContractorForm();
        contractor3.setName("Google");
        contractor3.setEmail("info@google.com");
        contractor3.setAddress("Mountain View");
        String contractorId2 = businessLogic.createContractor(contractor3, CREATOR_EMAIL);

        ContractorForm contractor4 = new ContractorForm();
        contractor4.setName("Apple");
        contractor4.setEmail("info@apple.com");
        contractor4.setAddress("Cupertino");
        businessLogic.createContractor(contractor4, CREATOR_EMAIL);

        ContractorForm contractor5 = new ContractorForm();
        contractor5.setName("Microsoft");
        contractor5.setEmail("info@microsoft.com");
        contractor5.setAddress("Redmont");
        businessLogic.createContractor(contractor5, CREATOR_EMAIL);

        String service1 = businessLogic.createContractorService("Consulting", contractorId1, CREATOR_EMAIL);
        businessLogic.createContractorService("Trading", contractorId1, CREATOR_EMAIL);
        businessLogic.createContractorService("Signing", contractorId1, CREATOR_EMAIL);

        businessLogic.createContractorService("Consulting", contractorId2, CREATOR_EMAIL);
        String service2 = businessLogic.createContractorService("Trading", contractorId2, CREATOR_EMAIL);
        businessLogic.createContractorService("Signing", contractorId2, CREATOR_EMAIL);

        ServiceRequestForm form1 = new ServiceRequestForm();
        form1.setName("test1");
        form1.setContractor(contractorId1);
        form1.setContractorService(service1);
        form1.setPrice(125);
        form1.setCurrency(Currency.PLN);
        form1.setLocation("E00-543");
        String serviceRequestId = businessLogic.createServiceRequest(form1, CREATOR_EMAIL);

        ServiceRequestForm form2 = new ServiceRequestForm();
        form2.setName("test2");
        form2.setContractor(contractorId2);
        form2.setContractorService(service2);
        form2.setPrice(250);
        form2.setCurrency(Currency.EUR);
        form2.setLocation("E00-544");
        businessLogic.createServiceRequest(form2, CREATOR_EMAIL);

        CommentForm commentForm1 = new CommentForm();
        commentForm1.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque mollis augue urna. Vivamus a orci in nulla eleifend scelerisque. Cras euismod lobortis blandit.");
        businessLogic.createComment(commentForm1, "dorian@asdf.pl", serviceRequestId);

        CommentForm commentForm2 = new CommentForm();
        commentForm2.setContent("Nunc et erat a leo elementum tristique. Fusce hendrerit fermentum ligula, vel auctor orci egestas non. In at tortor auctor, lobortis neque ac, mattis nunc.");
        businessLogic.createComment(commentForm2, "david@asdf.pl", serviceRequestId);

        CommentForm commentForm3 = new CommentForm();
        commentForm3.setContent("Maecenas mi sem, dignissim at nisi sit amet, dignissim congue turpis. In accumsan iaculis porttitor.");
        businessLogic.createComment(commentForm3, "dominic@asdf.pl", serviceRequestId);
    }
}
