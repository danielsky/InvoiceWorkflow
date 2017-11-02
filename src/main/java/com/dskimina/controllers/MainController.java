package com.dskimina.controllers;

import com.dskimina.enums.Currency;
import com.dskimina.enums.Result;
import com.dskimina.enums.Role;
import com.dskimina.exceptions.ObjectNotFoundException;
import com.dskimina.forms.CommentForm;
import com.dskimina.forms.ServiceRequestForm;
import com.dskimina.logic.BusinessLogic;
import com.dskimina.model.ContractorDTO;
import com.dskimina.model.ContractorServiceDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Controller
@Secured({Role.EMPLOYEE,Role.APPROVER,Role.ADMIN})
public class MainController {

    private static final Log LOG = LogFactory.getLog(MainController.class);
    public static final String COMMENT_FORM_ATTR = "commentForm";

    @Autowired
    private BusinessLogic businessLogic;

    @ModelAttribute
    public void addSelectorAttribute(ModelMap model){
        model.addAttribute("isListPage", true);
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/", "/index"})
    public String showServiceRequestList(ModelMap model, HttpServletRequest request){
        int type = 0;
        String tempType = request.getParameter("type");
        if(tempType != null){
            type = Integer.parseInt(tempType);
        }

        model.addAttribute("type", type);
        model.addAttribute("serviceRequests", businessLogic.getAllInvoices());
        return "service-requests";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/request/create")
    public String addServiceRequest(ModelMap model) throws ObjectNotFoundException{
        fillAddServiceRequestModel(model);
        model.addAttribute("serviceRequestForm", new ServiceRequestForm());
        return "add-new-request";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/request/create")
    public String addServiceRequestProcess(@Valid ServiceRequestForm serviceRequestForm, BindingResult bindingResult, ModelMap model, Principal principal, RedirectAttributes attributes) throws ObjectNotFoundException{
        LOG.info("Post processing");
        if (bindingResult.hasErrors()) {
            fillAddServiceRequestModel(model);
            return "add-new-request";
        }else {
            businessLogic.createServiceRequest(serviceRequestForm, principal.getName());
            attributes.addFlashAttribute("result", Result.SERVICE_REQUEST_CREATED);
        }
        return "redirect:/index";
    }

    private void fillAddServiceRequestModel(ModelMap  model) throws ObjectNotFoundException{
        model.addAttribute("locations", businessLogic.getLocations());
        List<ContractorDTO> contractors = businessLogic.getAllContractors();
        model.addAttribute("contractors", contractors);
        if(!contractors.isEmpty()) {
            model.addAttribute("contractorServices", businessLogic.getContractorServices(contractors.get(0).getIdentifier()));
        }else{
            model.addAttribute("contractorServices", Collections.emptyList());
        }
        model.addAttribute("currencies", Currency.values());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/request/{id}")
    public String showServiceRequest(@PathVariable("id") String identifier, ModelMap model) throws ObjectNotFoundException{
        model.addAttribute("serviceRequest", businessLogic.getServiceRequest(identifier));
        model.addAttribute("comments", businessLogic.getCommentsForServiceRequestId(identifier));
        model.addAttribute("workflow", businessLogic.getWorkflowForServiceRequestId(identifier));
        if (!model.containsAttribute(COMMENT_FORM_ATTR)) {
            model.addAttribute(COMMENT_FORM_ATTR, new CommentForm());
        }
        return "service-request";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/request/{id}/delete")
    public RedirectView deleteServiceRequest(@PathVariable("id") String identifier, RedirectAttributes attr) throws ObjectNotFoundException{
        businessLogic.removeServiceRequest(identifier);
        attr.addFlashAttribute("result", Result.SERVICE_REQUEST_DELETED);
        return new RedirectView("/index", true);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/request/{id}/increase")
    public RedirectView increaseStageForServiceRequest(@PathVariable("id") String identifier, Principal principal, RedirectAttributes attr) throws ObjectNotFoundException{
        businessLogic.moveServiceRequestToNextWorkflowStage(identifier, principal.getName());
        return new RedirectView("/request/"+identifier, true);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/role")
    public ResponseEntity getRoleEmployee(){
        return ResponseEntity.ok(Role.EMPLOYEE);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/request/{id}/document")
    public ResponseEntity uploadDocument(@PathVariable("id") String identifier, @RequestParam("media") MultipartFile file, RedirectAttributes attr) throws ObjectNotFoundException{
        LOG.info("File name: "+file.getName());
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/request/{id}/services")
    public ResponseEntity getContractorServices(@PathVariable("id") String identifier){
        List<ContractorServiceDTO> contractorServices;
        try {
            contractorServices = businessLogic.getContractorServices(identifier);
        } catch (ObjectNotFoundException e) {
            LOG.info("Cannot contractor for id "+identifier);
            return ResponseEntity.ok(Collections.emptyList());
        }
        return ResponseEntity.ok(contractorServices);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/request/{id}/comment")
    public RedirectView addComment(@PathVariable("id") String identifier, @Valid CommentForm commentForm, BindingResult bindingResult, Principal principal, RedirectAttributes ra) throws ObjectNotFoundException{
        String commentId = null;
        if (bindingResult.hasErrors()) {
            ra.addFlashAttribute(COMMENT_FORM_ATTR, commentForm);
            ra.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + COMMENT_FORM_ATTR, bindingResult);
            commentId = "#comment";
        }else {
            try {
                commentId = businessLogic.createComment(commentForm, principal.getName(), identifier);
            } catch (ObjectNotFoundException e) {
                LOG.info("Cannot contractor for id " + identifier);
                ra.addFlashAttribute("errorComment", true);
            }
            commentId = commentId != null ? ("#" + commentId) : "";
        }
        return new RedirectView("/request/"+identifier + commentId, true);
    }
}
