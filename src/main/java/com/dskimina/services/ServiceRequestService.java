package com.dskimina.services;

import com.dskimina.data.ServiceRequest;
import com.dskimina.data.User;
import com.dskimina.enums.WorkflowStep;
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

    public void deleteInvoice(String identifier){
        ServiceRequest serviceRequest = serviceRequestRepository.getByIdentifier(identifier);
        serviceRequestRepository.delete(serviceRequest);
    }

    public void createInvoice(String name, User creator, WorkflowStep workflowStep){

        ServiceRequest serviceRequest = new ServiceRequest();
        serviceRequest.setName(name);
        serviceRequest.setIdentifier(UUID.randomUUID().toString());
        serviceRequest.setWorkflowStep(workflowStep);
        serviceRequest.setCreator(creator);
        serviceRequest.setCreationTime(new Date());
        serviceRequestRepository.save(serviceRequest);
    }
}
