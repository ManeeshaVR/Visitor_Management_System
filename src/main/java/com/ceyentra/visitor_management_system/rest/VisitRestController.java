package com.ceyentra.visitor_management_system.rest;

import com.ceyentra.visitor_management_system.entity.Card;
import com.ceyentra.visitor_management_system.entity.Visit;
import com.ceyentra.visitor_management_system.entity.Visitor;
import com.ceyentra.visitor_management_system.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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
        Card availableCard = visitService.findAvailableCard();
        if (availableCard == null){
            throw new RuntimeException("All available cards have been assigned to visitors.");
        }else {
            visit.setVisitId(0);
            visit.setVisitor(visitService.findVisitor(visitorId));
            visit.setCard(availableCard);
            visit.setVisitDate(LocalDate.now());
            visit.setCheckIn(LocalTime.now());
            return visitService.saveVisit(visit);
        }
    }

    @PutMapping("/visits/{visitId}")
    public Visit updateVisit(@PathVariable int visitId){
        Visit visit = visitService.findVisitById(visitId);
        System.out.println(visit);
        visit.setCheckOut(LocalTime.now());
        return visitService.updateVisit(visit);
    }

    @GetMapping("/visits/{visitId}")
    public Visit getVisit(@PathVariable int visitId){
        return visitService.findVisitById(visitId);
    }

    @GetMapping("/visits")
    public List<Visit> getAllVisits(){
        return visitService.findAllVisits();
    }

    @GetMapping("/visits/nic/{visitorNic}")
    public List<Visit> getVisitsByNic(@PathVariable String visitorNic){
        return visitService.findVisitByVisitorNic(visitorNic);
    }

    @GetMapping("/visits/overdue")
    public List<Visit> getOverdueVisits(){
        return visitService.findOverdueVisits();
    }

}
