package com.dskimina.controllers;

import com.dskimina.enums.Result;
import com.dskimina.exceptions.ObjectNotFoundException;
import com.dskimina.forms.ContractorForm;
import com.dskimina.logic.BusinessLogic;
import com.dskimina.model.ContractorDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class ContractorsController {

    private static final Log LOG = LogFactory.getLog(ContractorsController.class);

    public static final String CONTRACTOR_ATTR = "contractor";

    @Autowired
    private BusinessLogic logic;

    @ModelAttribute
    public void addSelectorAttribute(ModelMap model){
        model.addAttribute("isContractorsPage", true);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/contractors")
    public String showContractors(ModelMap model){
        model.addAttribute("contractors", logic.getAllContractors());
        return "contractors";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/contractor/create")
    public String createContractor(ModelMap model){
        model.addAttribute("contractorForm", new ContractorForm());
        return "add-new-contractor";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/contractor/create")
    public String createContractorProcess(@Valid ContractorForm contractorForm, BindingResult bindingResult, Principal principal, RedirectAttributes attributes) throws ObjectNotFoundException{
        if (bindingResult.hasErrors()) {
            return "add-new-contractor";
        }
        String identifier = logic.createContractor(contractorForm, principal.getName());
        attributes.addFlashAttribute("result", Result.CONTRACTOR_CREATED);
        return "redirect:/contractor/"+identifier;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/contractor/{id}")
    public String showContractor(@PathVariable String id, ModelMap model) throws ObjectNotFoundException{
        if(!model.containsAttribute(CONTRACTOR_ATTR)) {
            ContractorDTO contractorDTO = logic.getContractorByIdentifier(id);
            model.addAttribute(CONTRACTOR_ATTR, contractorDTO);
        }
        model.addAttribute("services", logic.getContractorServices(id));
        return "contractor";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/contractor/{id}/update")
    public RedirectView editContractorProcess(@PathVariable String id, @Valid ContractorDTO contractorDTO, BindingResult bindingResult, RedirectAttributes ra){
        if (bindingResult.hasErrors()) {
            ra.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX+CONTRACTOR_ATTR, bindingResult);
            ra.addFlashAttribute(CONTRACTOR_ATTR, contractorDTO);
        }else {
            boolean updateResult = logic.updateContractor(id, contractorDTO);
            ra.addFlashAttribute("result", updateResult ? Result.CONTRACTOR_UPDATE_SUCCESS : Result.CONTRACTOR_UPDATE_FAILURE);
        }
        return new RedirectView("/contractor/"+id, true);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/contractor/{id}/service/{serviceId}/update")
    public ResponseEntity editContractorService(@PathVariable String id, @PathVariable String serviceId, @RequestParam String newName) throws ObjectNotFoundException{
        logic.updateContractorService(newName, serviceId);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/contractor/{id}/service/add")
    public ResponseEntity addContractorService(@PathVariable String id, @RequestParam String newName, Principal principal) throws ObjectNotFoundException{
        String identifier = logic.createContractorService(newName, id, principal.getName());
        return ResponseEntity.ok(identifier);
    }

}
