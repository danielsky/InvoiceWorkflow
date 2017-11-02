package com.dskimina.transformer;

import com.dskimina.data.*;
import com.dskimina.model.*;

public class DataTransformer {

    public static ServiceRequestDTO convert(ServiceRequest serviceRequest){
        ServiceRequestDTO dto = new ServiceRequestDTO();
        dto.setName(serviceRequest.getName());
        dto.setCreationTime(serviceRequest.getCreationTime());
        dto.setIdentifier(serviceRequest.getIdentifier());
        dto.setContractorName(serviceRequest.getContractor().getName());
        dto.setWorkflowStep(serviceRequest.getWorkflowStep());
        dto.setContractorService(serviceRequest.getServiceData().getName());
        dto.setPrice(serviceRequest.getPrice());
        dto.setCurrency(serviceRequest.getCurrency());
        dto.setLocation(serviceRequest.getLocation());
        dto.setNumber(serviceRequest.getNumber());
        return dto;
    }

    public static ContractorDTO convert(Contractor contractor){
        ContractorDTO dto = new ContractorDTO();
        dto.setName(contractor.getName());
        dto.setCreationTime(contractor.getCreationTime());
        dto.setIdentifier(contractor.getIdentifier());
        dto.setEmail(contractor.getEmail());
        dto.setTelephone(contractor.getTelephone());
        dto.setAddress(contractor.getAddress());
        return dto;
    }

    public static ContractorServiceDTO convert(ContractorServiceData contractorServiceData){
        ContractorServiceDTO dto = new ContractorServiceDTO();
        dto.setName(contractorServiceData.getName());
        dto.setId(contractorServiceData.getIdentifier());
        return dto;
    }

    public static CommentDTO convert(Comment comment){
        CommentDTO dto = new CommentDTO();
        dto.setAuthor(comment.getAuthor().getEmail());
        dto.setContent(comment.getContent());
        dto.setDate(comment.getCreationDate());
        dto.setIdentifier(comment.getIdentifier());
        return dto;
    }

    public static WorkflowStageDTO convert(WorkflowStage workflowStage){
        WorkflowStageDTO dto = new WorkflowStageDTO();
        dto.setDate(workflowStage.getDate());
        User owner = workflowStage.getOwner();
        dto.setOwner(owner != null ? owner.getEmail() : null);
        dto.setWorkflowStep(workflowStage.getWorkflowStep());
        dto.setDone(workflowStage.isDone());
        return dto;
    }

    public static DocumentDTO convert(Document document){
        DocumentDTO dto = new DocumentDTO();
        dto.setName(document.getName());
        dto.setIdentifier(document.getIdentifier());
        dto.setSize(document.getFile().getSize());
        return dto;
    }

}
