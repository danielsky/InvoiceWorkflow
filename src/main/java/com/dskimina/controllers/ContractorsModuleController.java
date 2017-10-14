package com.dskimina.controllers;

import com.dskimina.enums.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Secured(Role.CONTRACTOR)
@RequestMapping("/cmodule")
public class ContractorsModuleController {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public ResponseEntity index(){
        return ResponseEntity.ok("This will be contractor view");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/role")
    public ResponseEntity getRoleApprover(){
        return ResponseEntity.ok(Role.APPROVER);
    }
}
