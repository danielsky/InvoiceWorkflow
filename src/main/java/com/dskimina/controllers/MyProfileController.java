package com.dskimina.controllers;

import com.google.common.base.Joiner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
public class MyProfileController {

    @RequestMapping(method = RequestMethod.GET, value = {"/my-profile"})
    public String showUserProfile(ModelMap model, Principal principal){
        model.addAttribute("isMyProfile", true);
        model.addAttribute("user", principal.getName());
        Set<String> auths = new HashSet<>();
        for(GrantedAuthority ga : SecurityContextHolder.getContext().getAuthentication().getAuthorities()){
            auths.add(ga.getAuthority());
        }
        model.addAttribute("auth", Joiner.on(',').join(auths));
        return "my-profile";
    }
}
