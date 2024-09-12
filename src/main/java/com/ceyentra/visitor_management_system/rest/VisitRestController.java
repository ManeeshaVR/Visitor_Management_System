package com.ceyentra.visitor_management_system.rest;

import com.ceyentra.visitor_management_system.entity.Visit;
import com.ceyentra.visitor_management_system.entity.Visitor;
import com.ceyentra.visitor_management_system.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;

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

    @PostMapping("/visits/{visitorId}")
    public Visit saveVisit(@PathVariable int visitorId, @RequestBody Visit visit){
        visit.setVisitId(0);
        visit.setVisitor(visitService.findVisitor(visitorId));
        visit.setVisitDate(LocalDate.now());
        visit.setCheckIn(LocalTime.now());
        return visitService.save(visit);
    }

    @PutMapping("/visits/{visitId}")
    public Visit updateVisit(@PathVariable int visitId){
        Visit visit = visitService.findById(visitId);
        System.out.println(visit);
        visit.setCheckOut(LocalTime.now());
        return visitService.update(visit);
    }

}
