package com.dskimina.services;

import com.dskimina.data.Contractor;
import com.dskimina.data.ContractorServiceData;
import com.dskimina.data.ServiceRequest;
import com.dskimina.data.User;
import com.dskimina.enums.WorkflowStep;
import com.dskimina.forms.ServiceRequestForm;
import com.dskimina.repositories.ServiceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ServiceRequestService {

    @Autowired
    private ServiceRequestRepository serviceRequestRepository;

    public List<ServiceRequest> getAllServiceRequests(){
        List<ServiceRequest> serviceRequestList = new ArrayList<>();
        for(ServiceRequest serviceRequest : serviceRequestRepository.findAll()){
            serviceRequestList.add(serviceRequest);
        }
        return serviceRequestList;
    }

    public ServiceRequest getServiceRequest(String identifier){
        return serviceRequestRepository.getByIdentifier(identifier);
    }

    public void deleteServiceRequest(String identifier){
        ServiceRequest serviceRequest = serviceRequestRepository.getByIdentifier(identifier);
        serviceRequestRepository.delete(serviceRequest);
    }

    public void createServiceRequest(ServiceRequestForm form, Contractor contractor, ContractorServiceData contractorServiceData, User creator, WorkflowStep workflowStep){

        ServiceRequest serviceRequest = new ServiceRequest();
        serviceRequest.setName(form.getName());
        serviceRequest.setContractor(contractor);
        serviceRequest.setServiceData(contractorServiceData);
        serviceRequest.setIdentifier(UUID.randomUUID().toString());
        serviceRequest.setWorkflowStep(workflowStep);
        serviceRequest.setCreator(creator);
        serviceRequest.setCreationTime(new Date());
        serviceRequest.setPrice(form.getPrice());
        serviceRequest.setCurrency(form.getCurrency());
        serviceRequest.setLocation(form.getLocation());
        serviceRequestRepository.save(serviceRequest);
    }
}
