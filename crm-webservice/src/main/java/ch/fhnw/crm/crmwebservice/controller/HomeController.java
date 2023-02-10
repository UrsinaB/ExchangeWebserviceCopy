package ch.fhnw.crm.crmwebservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Hidden;

import java.security.Principal;

@Hidden
@RestController
public class HomeController {

    @GetMapping("/home")
    public String home(Principal principal) {
        return "Hello, " + principal.getName();
    }

    @GetMapping("/")
    public String getHello() {     

        return "Hey there, welcome to CRM Webservice";
    }
    
       
    }
