package com.dskimina.controllers;

import com.dskimina.logic.BusinessLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller
public class SecurityController {

    @Autowired
    private BusinessLogic logic;

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String showLoginPage(){
        return "login";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/logout")
    public RedirectView logout(HttpSession session){
        session.invalidate();
        return new RedirectView("/login?logout", true);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/authorize")
    public RedirectView authorizeProcess(){
        return new RedirectView("/index",true);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/reset-pass")
    public String showResetPasswordPage(){
        return "reset-pass";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/reset-pass")
    public RedirectView showResetPasswordPageProcess(@RequestParam String email, RedirectAttributes ra){
        boolean result = logic.createResetPasswordLinkForEmail(email);
        ra.addFlashAttribute("result", result);
        return new RedirectView("reset-pass");
    }
}
