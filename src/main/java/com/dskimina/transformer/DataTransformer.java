package com.dskimina.transformer;

import com.dskimina.data.Contractor;
import com.dskimina.data.Invoice;
import com.dskimina.model.ContractorDTO;
import com.dskimina.model.InvoiceDTO;

public class DataTransformer {

    public static InvoiceDTO convert(Invoice invoice){
        InvoiceDTO dto = new InvoiceDTO();
        dto.setName(invoice.getName());
        dto.setCreationTime(invoice.getCreationTime());
        dto.setIdentifier(invoice.getIdentifier());
        dto.setWorkflowStep(invoice.getWorkflowStep());
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

}
