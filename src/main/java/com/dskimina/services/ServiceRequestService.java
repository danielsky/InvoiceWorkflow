package com.dskimina.services;

import com.dskimina.data.*;
import com.dskimina.enums.WorkflowStep;
import com.dskimina.exceptions.ObjectNotFoundException;
import com.dskimina.forms.ServiceRequestForm;
import com.dskimina.repositories.CommentRepository;
import com.dskimina.repositories.ServiceRequestRepository;
import com.dskimina.repositories.WorkflowStageRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ServiceRequestService {

    private static final Log LOG = LogFactory.getLog(ServiceRequestService.class);

    @Autowired
    private ServiceRequestRepository serviceRequestRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private WorkflowStageRepository workflowStageRepository;

    public List<ServiceRequest> getAllServiceRequests(){
        List<ServiceRequest> serviceRequestList = new ArrayList<>();
        for(ServiceRequest serviceRequest : serviceRequestRepository.findAll()){
            serviceRequestList.add(serviceRequest);
        }
        return serviceRequestList;
    }

    public ServiceRequest getServiceRequest(String identifier) throws ObjectNotFoundException{
        ServiceRequest serviceRequest = serviceRequestRepository.getByIdentifier(identifier);
        if(serviceRequest == null){
            throw new ObjectNotFoundException("Service request not found by id: "+identifier);
        }
        return serviceRequest;
    }

    public void deleteServiceRequest(String identifier) throws ObjectNotFoundException{
        ServiceRequest serviceRequest = getServiceRequest(identifier);
        for(Comment comment : commentRepository.findByServiceRequest(serviceRequest)){
            commentRepository.delete(comment);
        }
        for(WorkflowStage workflowStage : workflowStageRepository.findByServiceRequest(serviceRequest)){
            workflowStageRepository.delete(workflowStage);
        }
        serviceRequestRepository.delete(serviceRequest);
    }

    public ServiceRequest createServiceRequest(ServiceRequestForm form, Contractor contractor, ContractorServiceData contractorServiceData, User creator){
        ServiceRequest serviceRequest = new ServiceRequest();
        serviceRequest.setName(form.getName());
        serviceRequest.setContractor(contractor);
        serviceRequest.setServiceData(contractorServiceData);
        serviceRequest.setIdentifier(UUID.randomUUID().toString());
        serviceRequest.setWorkflowStep(WorkflowStep.WAITING_FOR_FIRST_APPROVE);
        serviceRequest.setCreator(creator);
        serviceRequest.setCreationTime(new Date());
        serviceRequest.setPrice(form.getPrice());
        serviceRequest.setCurrency(form.getCurrency());
        serviceRequest.setLocation(form.getLocation());

        Integer number = serviceRequestRepository.findMaxNumber();
        serviceRequest.setNumber(number+1);
        return serviceRequestRepository.save(serviceRequest);
    }
}
