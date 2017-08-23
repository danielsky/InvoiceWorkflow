package com.dskimina.logic;

import com.dskimina.data.Invoice;
import com.dskimina.data.User;
import com.dskimina.enums.Role;
import com.dskimina.enums.WorkflowStep;
import com.dskimina.services.InvoiceService;
import com.dskimina.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BusinessLogic {

    @Autowired
    private UserService userService;

    @Autowired
    private InvoiceService invoiceService;


    public void createUser(String name, String surname, String email, String password, Role role){
        userService.createUser(name, surname, email, password, role);
    }

    public void createInvoice(String name, String creatorEmail, WorkflowStep step){
        User creator = userService.getByEmail(creatorEmail);
        if(creator == null){
            throw new IllegalStateException("Cannot find currently logged user in database: "+creatorEmail);
        }
        invoiceService.createInvoice(name, creator, step);
    }

    public List<Invoice> getAllInvoices(){
        return invoiceService.getAllInvoices();
    }

    public Invoice getInvoice(String identifier){
        return invoiceService.getInvoice(identifier);
    }

    public void removeInvoice(String identifier) {
        invoiceService.deleteInvoice(identifier);
    }
}
