package com.ceyentra.visitor_management_system.rest;

import com.ceyentra.visitor_management_system.entity.Visitor;
import com.ceyentra.visitor_management_system.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class VisitorRestController {

    private VisitorService visitorService;

    @Autowired
    public VisitorRestController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @GetMapping("/health")
    public String checkHealth(){
        return "Hello Visitor";
    }

    @PostMapping("/visitors")
    public Visitor saveVisitor(@RequestBody Visitor visitor){
        visitor.setVisitorId(0);
        return visitorService.save(visitor);
    }

}
