package com.dskimina.controllers.contractor;

import com.dskimina.enums.Role;
import com.dskimina.logic.ContractorLogic;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@Secured(Role.CONTRACTOR)
@RequestMapping("/cmodule")
public class ContractorsModuleController {

    private static final Log LOG = LogFactory.getLog(ContractorsModuleController.class);
    public static final String MODULE_PREFIX = "cmodule/";

    @Autowired
    private ContractorLogic contractorLogic;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String index(ModelMap model, Principal principal, @RequestParam(defaultValue = "0") String type){
        int typeNumber = 0;
        if(type.equals("1")) typeNumber = 1;

        model.addAttribute("type", typeNumber);
        model.addAttribute("serviceRequests", contractorLogic.getServiceRequestForContractor(principal.getName()));
        return MODULE_PREFIX + "index";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/role")
    public ResponseEntity getRoleApprover(){
        return ResponseEntity.ok(Role.APPROVER);
    }
}
