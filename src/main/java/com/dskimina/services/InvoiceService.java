package com.dskimina.services;

import com.dskimina.data.Invoice;
import com.dskimina.data.User;
import com.dskimina.enums.WorkflowStep;
import com.dskimina.repositories.InvoiceRepository;
import com.dskimina.repositories.UserRepository;
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

    @Autowired
    private UserRepository userRepository;

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

    public void createInvoice(String name, String userEmail, WorkflowStep workflowStep){

        User creator = userRepository.getByEmail(userEmail);
        if(creator == null){
            throw new IllegalStateException("Cannot find currently logged user in database: "+userEmail);
        }

        Invoice invoice = new Invoice();
        invoice.setName(name);
        invoice.setIdentifier(UUID.randomUUID().toString());
        invoice.setWorkflowStep(workflowStep);
        invoice.setCreator(creator);
        invoice.setCreationTime(new Date());
        invoiceRepository.save(invoice);
    }
}
