package com.dskimina.controllers;

import com.dskimina.enums.Result;
import com.dskimina.forms.ServiceRequestForm;
import com.dskimina.logic.BusinessLogic;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class MainController {

    private static final Log LOG = LogFactory.getLog(MainController.class);

    @Autowired
    private BusinessLogic businessLogic;

    @ModelAttribute
    public void addSelectorAttribute(ModelMap model){
        model.addAttribute("isListPage", true);
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/", "/index"})
    public String showServiceRequestList(ModelMap model){
        model.addAttribute("serviceRequests", businessLogic.getAllInvoices());
        return "service-requests";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/request/create")
    public String addServiceRequest(ModelMap model, ServiceRequestForm serviceRequestForm){
        model.addAttribute("locations", businessLogic.getLocations());
        model.addAttribute("contractors", businessLogic.getAllContractors());
        return "add-new-request";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/request/create")
    public RedirectView addServiceRequestProcess(@Valid ServiceRequestForm serviceRequestForm, BindingResult bindingResult, Principal principal, RedirectAttributes attributes){
        LOG.info("Post processing");
        if (bindingResult.hasErrors()) {
            attributes.addFlashAttribute("result", Result.SERVICE_REQUEST_CREATION_ERROR);
        }else {
            businessLogic.createServiceRequest(serviceRequestForm, principal.getName());
            attributes.addFlashAttribute("result", Result.SERVICE_REQUEST_CREATED);
        }
        return new RedirectView("/index", true);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/request/{id}")
    public String showServiceRequest(@PathVariable("id") String identifier, ModelMap model){
        model.addAttribute("serviceRequest", businessLogic.getServiceRequest(identifier));
        return "service-request";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/request/{id}/delete")
    public RedirectView deleteServiceRequest(@PathVariable("id") String identifier, RedirectAttributes attr){
        businessLogic.removeServiceRequest(identifier);
        attr.addFlashAttribute("result", Result.SERVICE_REQUEST_DELETED);
        return new RedirectView("/index", true);
    }
}
