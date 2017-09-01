package com.dskimina.controllers;

import com.dskimina.enums.Result;
import com.dskimina.logic.BusinessLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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
    public String createContractor(){
        return "add-new-contractor";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/contractor/create")
    public RedirectView createContractorProcess(RedirectAttributes attributes){
        attributes.addFlashAttribute("result", Result.CONTRACTOR_CREATED);
        return new RedirectView("/contractors", true);
    }

}
