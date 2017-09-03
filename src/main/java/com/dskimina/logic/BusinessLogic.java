package com.dskimina.logic;

import com.dskimina.data.Contractor;
import com.dskimina.data.ServiceRequest;
import com.dskimina.data.User;
import com.dskimina.enums.Role;
import com.dskimina.enums.WorkflowStep;
import com.dskimina.exceptions.ObjectNotFoundException;
import com.dskimina.forms.ContractorForm;
import com.dskimina.forms.ServiceRequestForm;
import com.dskimina.model.ContractorDTO;
import com.dskimina.model.ServiceRequestDTO;
import com.dskimina.services.ContractorService;
import com.dskimina.services.MailService;
import com.dskimina.services.ServiceRequestService;
import com.dskimina.services.UserService;
import com.dskimina.transformer.DataTransformer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class BusinessLogic {

    private static final Log LOG = LogFactory.getLog(BusinessLogic.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ServiceRequestService serviceRequestService;

    @Autowired
    private MailService mailService;

    @Autowired
    private ContractorService contractorService;


    public void createUser(String name, String surname, String email, String password, Role role){
        userService.createUser(name, surname, email, password, role);
    }

    public void createServiceRequest(ServiceRequestForm serviceRequestForm, String creatorEmail) throws ObjectNotFoundException{
        User creator = userService.getByEmail(creatorEmail);
        serviceRequestService.createInvoice(serviceRequestForm.getName(), creator, WorkflowStep.WAITING_FOR_FIRST_APPROVE);

        String content = mailService.prepareMessage("test user");
        mailService.sendEmail("daniels@asdf.pl", content, "New ServiceRequest created");

    }

    public List<ServiceRequestDTO> getAllInvoices(){
        List<ServiceRequestDTO> dtoList = new ArrayList<>();
        for(ServiceRequest serviceRequest : serviceRequestService.getAllServiceRequests()){
            dtoList.add(DataTransformer.convert(serviceRequest));
        }
        return dtoList;
    }

    public List<ContractorDTO> getAllContractors(){
        List<ContractorDTO> dtoList = new ArrayList<>();
        for(Contractor contractor : contractorService.getAllContractors()){
            dtoList.add(DataTransformer.convert(contractor));
        }
        return dtoList;
    }

    public String createContractor(ContractorForm contractorForm, String creatorEmail) throws ObjectNotFoundException{
        User creator = userService.getByEmail(creatorEmail);
        return contractorService.createContractor(contractorForm, creator);
    }

    public ContractorDTO getContractorByIdentifier(String identifier) throws ObjectNotFoundException{
        Contractor contractor = contractorService.getContractorByIdentifier(identifier);
        return DataTransformer.convert(contractor);
    }

    public boolean updateContractor(String id, ContractorDTO contractorDTO){
        try {
            contractorService.updateContractor(id, contractorDTO);
            return true;
        }catch(ObjectNotFoundException ex){
            return false;
        }
    }

    public ServiceRequest getServiceRequest(String identifier){
        return serviceRequestService.getServiceRequest(identifier);
    }

    public void removeServiceRequest(String identifier) {
        serviceRequestService.deleteInvoice(identifier);
    }

    public Set<String> getLocations(){
        Set<String> locations = new HashSet<>();
        Random rand = new Random();
        for(int i=0;i<20;i++){
            int n = 400 + rand.nextInt(200);
            locations.add("E00-"+Integer.toString(n));
        }
        return locations;
    }
}
