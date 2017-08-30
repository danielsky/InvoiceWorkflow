package com.dskimina.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ContractorsController {

    @RequestMapping(method = RequestMethod.GET, value = "/contractors")
    public String showContractors(ModelMap model){
        model.addAttribute("isContractorsPage", true);
        return "contractors";
    }

}
