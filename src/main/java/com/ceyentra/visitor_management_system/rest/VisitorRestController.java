package com.ceyentra.visitor_management_system.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/visitors")
public class VisitorRestController {

    @GetMapping("/health")
    public String checkHealth(){
        return "Hello Visitor";
    }

}
