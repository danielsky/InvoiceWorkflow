package com.dskimina.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/external")
public class NoAuthController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String acceptServiceRequestFirstTime(){
        return "external/acceptance_result";
    }


}
