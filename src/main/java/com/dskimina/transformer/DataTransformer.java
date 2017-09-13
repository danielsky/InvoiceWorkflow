package com.dskimina.transformer;

import com.dskimina.data.Contractor;
import com.dskimina.data.ContractorServiceData;
import com.dskimina.data.ServiceRequest;
import com.dskimina.model.ContractorDTO;
import com.dskimina.model.ContractorServiceDTO;
import com.dskimina.model.ServiceRequestDTO;

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

}
