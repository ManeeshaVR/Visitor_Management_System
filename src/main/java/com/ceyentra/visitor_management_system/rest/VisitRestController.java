package com.ceyentra.visitor_management_system.rest;

import com.ceyentra.visitor_management_system.entity.Card;
import com.ceyentra.visitor_management_system.entity.Employee;
import com.ceyentra.visitor_management_system.entity.Visit;
import com.ceyentra.visitor_management_system.entity.Visitor;
import com.ceyentra.visitor_management_system.exception.NotFoundException;
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

    @PostMapping("/visits/{visitorId}/{employeeId}")
    public Visit saveVisit(@PathVariable int visitorId, @PathVariable int employeeId, @RequestBody String purpose){
        Card availableCard = visitService.findAvailableCard();
        Employee employee = visitService.findEmployee(employeeId);
        Visit visit = new Visit();
        if (availableCard == null){
            throw new NotFoundException("All available cards have been assigned to visitors.");
        }else if(employee == null){
            throw new NotFoundException("Employee not found with employee ID : " + employee.getId());
        }
        else {
            visit.setVisitId(0);
            visit.setVisitor(visitService.findVisitor(visitorId));
            visit.setEmployee(employee);
            visit.setCard(availableCard);
            visit.setVisitDate(LocalDate.now());
            visit.setCheckIn(LocalTime.now());
            visit.setPurpose(purpose);
            return visitService.saveVisit(visit);
        }
    }

    @PutMapping("/visits/{visitId}")
    public Visit updateVisit(@PathVariable int visitId){
        if (visitService.existsVisit(visitId)){
            Visit visit = visitService.findVisitById(visitId);
            visit.setCheckOut(LocalTime.now());
            return visitService.updateVisit(visit);
        }else {
            throw new NotFoundException("Visit not found with visit ID : " + visitId);
        }
    }

    @GetMapping("/visits/{visitId}")
    public Visit getVisit(@PathVariable int visitId){
        if (visitService.existsVisit(visitId)){
            return visitService.findVisitById(visitId);
        }else {
            throw new NotFoundException("Visit not found with visit ID : " + visitId);
        }
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
