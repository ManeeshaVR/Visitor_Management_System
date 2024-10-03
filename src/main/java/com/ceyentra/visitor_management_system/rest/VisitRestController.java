package com.ceyentra.visitor_management_system.rest;

import com.ceyentra.visitor_management_system.dto.VisitDTO;
import com.ceyentra.visitor_management_system.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public String checkHealth() {
        return "Hello Visit";
    }

    @PostMapping("/visits/{visitorId}/{employeeId}")
    public VisitDTO saveVisit(@PathVariable int visitorId, @PathVariable int employeeId, @RequestBody String purpose) {
        VisitDTO visit = new VisitDTO();
        visit.setVisitId(0);
        visit.setPurpose(purpose);
        return visitService.saveVisit(visit, employeeId, visitorId);
    }

    @PutMapping("/visits/{visitId}")
    public VisitDTO updateVisit(@PathVariable int visitId) {
        return visitService.updateVisit(visitId);
    }

    @GetMapping("/visits/{visitId}")
    public VisitDTO getVisit(@PathVariable int visitId) {
        return visitService.findVisitById(visitId);
    }

    @GetMapping("/visits")
    public List<VisitDTO> getAllVisits() {
        return visitService.findAllVisits();
    }

    @GetMapping("/visits/nic/{visitorNic}")
    public List<VisitDTO> getVisitsByNic(@PathVariable String visitorNic) {
        return visitService.findVisitByVisitorNic(visitorNic);
    }

    @GetMapping("/visits/overdue")
    public List<VisitDTO> getOverdueVisits() {
        return visitService.findOverdueVisits();
    }

}
