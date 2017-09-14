package com.dskimina.logic;

import com.dskimina.data.Contractor;
import com.dskimina.data.ContractorServiceData;
import com.dskimina.data.ServiceRequest;
import com.dskimina.data.User;
import com.dskimina.enums.Role;
import com.dskimina.enums.WorkflowStep;
import com.dskimina.exceptions.ObjectNotFoundException;
import com.dskimina.forms.ContractorForm;
import com.dskimina.forms.ServiceRequestForm;
import com.dskimina.model.ContractorDTO;
import com.dskimina.model.ContractorServiceDTO;
import com.dskimina.model.ServiceRequestDTO;
import com.dskimina.services.ContractorService;
import com.dskimina.services.MailService;
import com.dskimina.services.ServiceRequestService;
import com.dskimina.services.UserService;
import com.dskimina.transformer.DataTransformer;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        Contractor contractor = contractorService.getContractorByIdentifier(serviceRequestForm.getContractor());
        ContractorServiceData contractorServiceData = contractorService.getContractorServiceByIdentifier(serviceRequestForm.getContractorService());
        serviceRequestService.createServiceRequest(serviceRequestForm, contractor, contractorServiceData, creator, WorkflowStep.WAITING_FOR_FIRST_APPROVE);

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

    public ServiceRequestDTO getServiceRequest(String identifier) throws ObjectNotFoundException{
        return DataTransformer.convert(serviceRequestService.getServiceRequest(identifier));
    }

    public void removeServiceRequest(String identifier) throws ObjectNotFoundException{
        serviceRequestService.deleteServiceRequest(identifier);
    }

    public List<String> getLocations(){
        try {
            return IOUtils.readLines(BusinessLogic.class.getResourceAsStream("/locations/locations.dat"), "UTF8");
        } catch (IOException e) {
            LOG.info("Problem with reading locations", e);
            return Collections.emptyList();
        }
    }

    public List<ContractorServiceDTO> getContractorServices(String contractorId) throws ObjectNotFoundException{
        /*try {
            List<ContractorServiceDTO> services = new LinkedList<>();
            List<String> names =  IOUtils.readLines(BusinessLogic.class.getResourceAsStream("/locations/services.dat"), "UTF8");
            for(String name : names){
                ContractorServiceDTO dto = new ContractorServiceDTO();
                dto.setId(UUID.randomUUID().toString());
                dto.setName(name);
                services.add(dto);
            }
            return services;
        } catch (IOException e) {
            LOG.info("Problem with reading locations", e);
            return Collections.emptyList();
        }*/
        List<ContractorServiceDTO> dtoList = new ArrayList<>();
        for(ContractorServiceData contractorServiceData : contractorService.getContractorServicesByContractorIdentifier(contractorId)){
            dtoList.add(DataTransformer.convert(contractorServiceData));
        }
        return dtoList;
    }

    public String createContractorService(String serviceName, String contractorId, String creatorEmail) throws ObjectNotFoundException{
        User creator = userService.getByEmail(creatorEmail);
        Contractor contractor = contractorService.getContractorByIdentifier(contractorId);
        return contractorService.createContractorService(serviceName, contractor, creator);
    }

    public void updateContractorService(String newName, String contractorServiceId) throws ObjectNotFoundException{
        ContractorServiceDTO contractorServiceDTO = new ContractorServiceDTO();
        contractorServiceDTO.setName(newName);
        contractorService.updateContractorService(contractorServiceId, contractorServiceDTO);
    }

    public void removeContractorService(String contractorServiceId) throws ObjectNotFoundException{
        contractorService.removeContractorService(contractorServiceId);
    }
}
