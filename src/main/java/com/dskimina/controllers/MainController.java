package com.dskimina.controllers;

import com.dskimina.enums.Result;
import com.dskimina.forms.InvoiceForm;
import com.dskimina.logic.BusinessLogic;
import com.dskimina.validators.InvoiceValidator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class MainController {

    private static final Log LOG = LogFactory.getLog(MainController.class);

    @Autowired
    private InvoiceValidator invoiceValidator;

    @Autowired
    private BusinessLogic businessLogic;

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(invoiceValidator);
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/", "/index"})
    public String showInvoiceList(ModelMap model){
        model.addAttribute("isListPage", true);
        model.addAttribute("invoices", businessLogic.getAllInvoices());
        return "index";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/add-new-invoice")
    public String addNewInvoice(ModelMap modelMap){
        modelMap.addAttribute("isAddRequest", true);
        return "add-new-invoice";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add-new-invoice")
    public RedirectView addNewInvoiceProcess(@Valid @ModelAttribute("form") InvoiceForm invoiceForm, BindingResult bindingResult, Principal principal, RedirectAttributes attributes){
        LOG.info("Post processing");
        if (bindingResult.hasErrors()) {
            attributes.addFlashAttribute("result", Result.INVOICE_CREATION_ERROR);
            return new RedirectView("/index");
        }else {
            businessLogic.createInvoice(invoiceForm, principal.getName());
            attributes.addFlashAttribute("result", Result.INVOICE_CREATED);
            return new RedirectView("/index");
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/my-profile"})
    public String showUserProfile(ModelMap model, Principal principal){
        model.addAttribute("isMyProfile", true);
        model.addAttribute("user", principal.getName());
        return "my-profile";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/invoice/{id}")
    public String showInvoice(@PathVariable("id") String identifier, ModelMap model){
        model.addAttribute("invoice", businessLogic.getInvoice(identifier));
        return "invoice-details";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/invoice/{id}/delete")
    public RedirectView deleteInvoice(@PathVariable("id") String identifier, RedirectAttributes attr){
        businessLogic.removeInvoice(identifier);
        attr.addFlashAttribute("result", Result.INVOICE_DELETED);
        return new RedirectView("/index");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/contractors")
    public String showContractors(ModelMap model){
        model.addAttribute("isContractorsPage", true);
        return "contractors";
    }


}
