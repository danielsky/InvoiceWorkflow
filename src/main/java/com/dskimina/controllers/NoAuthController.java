package com.dskimina.controllers;

import com.dskimina.exceptions.ObjectNotFoundException;
import com.dskimina.logic.AcceptanceLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/external")
public class NoAuthController {

    @Autowired
    private AcceptanceLogic acceptanceLogic;

    @RequestMapping(value = "/accept-first", method = RequestMethod.GET, params = {"serviceRequest", "securityCode"})
    public String acceptServiceRequestFirstTime(@RequestParam String serviceRequest, @RequestParam String securityCode) throws ObjectNotFoundException{

        acceptanceLogic.moveServiceRequestToNextWorkflowStage(serviceRequest, securityCode);


        return "external/acceptance_result";
    }


}
