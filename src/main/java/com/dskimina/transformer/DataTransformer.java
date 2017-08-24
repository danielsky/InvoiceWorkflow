package com.dskimina.transformer;

import com.dskimina.data.Invoice;
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

}
