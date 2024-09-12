package com.ceyentra.visitor_management_system.rest;

import com.ceyentra.visitor_management_system.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class VisitRestController {

    private VisitService visitService;

    @Autowired
    public VisitRestController(VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping("/check")
    public String checkHealth(){
        return "Hello Visit";
    }

}
