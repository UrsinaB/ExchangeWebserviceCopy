package ch.fhnw.crm.exchangeWebservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Hidden;

@Hidden
@RestController
public class HomeController {

    @GetMapping("/home")
    public String getHello() {     

        return "Hey there, welcome to CRM Webservice";
    }

}

    
       
    
