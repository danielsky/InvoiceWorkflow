package com.dskimina.controllers;

import com.dskimina.enums.Role;
import com.dskimina.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Secured(Role.ADMIN)
public class AdminController {

    @Autowired
    private LogService logService;

    @RequestMapping(method = RequestMethod.GET, value = "/logs")
    public String getLogsView(ModelMap model){
        model.addAttribute("logs", logService.getAllLogs());
        return "logs";
    }
}
