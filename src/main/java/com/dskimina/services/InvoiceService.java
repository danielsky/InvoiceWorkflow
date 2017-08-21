package com.dskimina.services;

import com.dskimina.data.Invoice;
import com.dskimina.data.User;
import com.dskimina.enums.WorkflowStep;
import com.dskimina.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public List<Invoice> getAllInvoices(){
        List<Invoice> invoiceList = new ArrayList<>();
        for(Invoice invoice : invoiceRepository.findAll()){
            invoiceList.add(invoice);
        }
        return invoiceList;
    }

    public Invoice getInvoice(String identifier){
        return invoiceRepository.getByIdentifier(identifier);
    }

    public void createInvoice(String name, User creator, WorkflowStep workflowStep){

        Invoice invoice = new Invoice();
        invoice.setName(name);
        invoice.setIdentifier(UUID.randomUUID().toString());
        invoice.setWorkflowStep(workflowStep);
        invoice.setCreator(creator);
        invoice.setCreationTime(new Date());
        invoiceRepository.save(invoice);
    }
}
