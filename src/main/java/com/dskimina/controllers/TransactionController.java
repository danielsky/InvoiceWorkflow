package com.dskimina.controllers;

import com.dskimina.logic.BusinessLogic;
import com.dskimina.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class TransactionController {

    @Autowired
    private UserService userService;

    @Autowired
    private BusinessLogic logic;

    @RequestMapping(method = RequestMethod.GET, value = "/transaction")
    public String getTransView(ModelMap model){
        model.addAttribute("users", userService.getAllUsers());
        return "transaction";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/transactionEx")
    public RedirectView getTransViewEx(RedirectAttributes ra){
        return new RedirectView("/transaction");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/transaction")
    public RedirectView getTransViewNormal(){
        return new RedirectView("/transaction");
    }
}
