package com.dskimina.controllers;

import com.dskimina.enums.Result;
import com.dskimina.forms.ContractorForm;
import com.dskimina.logic.BusinessLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class ContractorsController {

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
    public String createContractor(ContractorForm contractorForm){
        return "add-new-contractor";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/contractor/create")
    public String createContractorProcess(@Valid ContractorForm contractorForm, BindingResult bindingResult, Principal principal, RedirectAttributes attributes){
        if (bindingResult.hasErrors()) {
            return "add-new-contractor";
        }

        String identifier = logic.createContractor(contractorForm, principal.getName());
        attributes.addFlashAttribute("result", Result.CONTRACTOR_CREATED);
        return "redirect:/contractors";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/contractor/{id}")
    public String editContractor(@PathVariable String id, ModelMap model){
        return "contractor";
    }

}
