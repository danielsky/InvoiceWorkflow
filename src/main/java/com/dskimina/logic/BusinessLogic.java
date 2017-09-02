package com.dskimina.logic;

import com.dskimina.data.Contractor;
import com.dskimina.data.Invoice;
import com.dskimina.data.User;
import com.dskimina.enums.Role;
import com.dskimina.enums.WorkflowStep;
import com.dskimina.forms.ContractorForm;
import com.dskimina.forms.InvoiceForm;
import com.dskimina.model.ContractorDTO;
import com.dskimina.model.InvoiceDTO;
import com.dskimina.services.ContractorService;
import com.dskimina.services.InvoiceService;
import com.dskimina.services.MailService;
import com.dskimina.services.UserService;
import com.dskimina.transformer.DataTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BusinessLogic {

    @Autowired
    private UserService userService;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private MailService mailService;

    @Autowired
    private ContractorService contractorService;


    public void createUser(String name, String surname, String email, String password, Role role){
        userService.createUser(name, surname, email, password, role);
    }

    public void createInvoice(InvoiceForm invoiceForm, String creatorEmail){
        User creator = userService.getByEmail(creatorEmail);
        if(creator == null){
            throw new IllegalStateException("Cannot find currently logged user in database: "+creatorEmail);
        }
        WorkflowStep workflowStep = invoiceForm.isSendNow() ? WorkflowStep.WAITING_FOR_FIRST_APPROVE : WorkflowStep.CREATED;
        invoiceService.createInvoice(invoiceForm.getName(), creator, workflowStep);
        if(invoiceForm.isSendNow()){
            String content = mailService.prepareMessage("test user");
            mailService.sendEmail("dskimina@gmail.com", content, "New Invoice created");
        }
    }

    public List<InvoiceDTO> getAllInvoices(){
        List<InvoiceDTO> dtoList = new ArrayList<>();
        for(Invoice invoice : invoiceService.getAllInvoices()){
            dtoList.add(DataTransformer.convert(invoice));
        }
        return dtoList;
    }

    public List<ContractorDTO> getAllContractors(){
        List<ContractorDTO> dtoList = new ArrayList<>();
        for(Contractor contractor : contractorService.getAllContractors()){
            dtoList.add(DataTransformer.convert(contractor));
        }
        return dtoList;
    }

    public String createContractor(ContractorForm contractorForm, String creatorEmail){
        User creator = userService.getByEmail(creatorEmail);
        if(creator == null){
            throw new IllegalStateException("Cannot find currently logged user in database: "+creatorEmail);
        }
        return contractorService.createContractor(contractorForm, creator);
    }

    public Invoice getInvoice(String identifier){
        return invoiceService.getInvoice(identifier);
    }

    public void removeInvoice(String identifier) {
        invoiceService.deleteInvoice(identifier);
    }
}
