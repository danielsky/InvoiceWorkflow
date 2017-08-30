package com.dskimina.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class MyProfileController {

    @RequestMapping(method = RequestMethod.GET, value = {"/my-profile"})
    public String showUserProfile(ModelMap model, Principal principal){
        model.addAttribute("isMyProfile", true);
        model.addAttribute("user", principal.getName());
        return "my-profile";
    }
}
