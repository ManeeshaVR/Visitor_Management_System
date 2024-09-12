package com.ceyentra.visitor_management_system.rest;

import com.ceyentra.visitor_management_system.entity.Visitor;
import com.ceyentra.visitor_management_system.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/visitors/{visitorId}")
    public Visitor updateVisitor(@PathVariable int visitorId, @RequestBody Visitor visitor){
        visitor.setVisitorId(visitorId);
        return visitorService.update(visitor);
    }

    @DeleteMapping("/visitors/{visitorId}")
    public void deleteVisitor(@PathVariable int visitorId){
        visitorService.delete(visitorId);
    }

    @GetMapping("/visitors/{visitorId}")
    public Visitor getVisitor(@PathVariable int visitorId){
        return visitorService.findById(visitorId);
    }

}
