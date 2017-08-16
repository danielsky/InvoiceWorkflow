package com.dskimina;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller
public class SecurityController {

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String showLoginPage(){
        return "login";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/logout")
    public RedirectView logout(HttpSession session){
        session.invalidate();
        return new RedirectView("/login?logout");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/authorize")
    public RedirectView authorizeProcess(){
        return new RedirectView("/index");
    }
}
