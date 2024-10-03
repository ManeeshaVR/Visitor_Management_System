package com.ceyentra.visitor_management_system.rest;

import com.ceyentra.visitor_management_system.dto.VisitDTO;
import com.ceyentra.visitor_management_system.exception.ResourceNotFoundException;
import com.ceyentra.visitor_management_system.service.VisitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class VisitRestController {

    private VisitService visitService;
    private static final Logger logger = LoggerFactory.getLogger(VisitRestController.class);

    @Autowired
    public VisitRestController(VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping("/check")
    public String checkHealth() {
        return "Hello Visit";
    }

    @PostMapping("/visits/{visitorId}/{employeeId}")
    public ResponseEntity<?> saveVisit(@PathVariable int visitorId, @PathVariable int employeeId, @RequestBody String purpose) {
        logger.info("Received request for save a visit");
        try {
            VisitDTO visit = new VisitDTO();
            visit.setVisitId(0);
            visit.setPurpose(purpose);
            return ResponseEntity.ok(visitService.saveVisit(visit, employeeId, visitorId));
        } catch (Exception e) {
            logger.error("An exception occurred: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/visits/{visitId}")
    public ResponseEntity<?> updateVisit(@PathVariable int visitId) {
        logger.info("Received request for update a visit");
        try {
            return ResponseEntity.ok(visitService.updateVisit(visitId));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("An exception occurred: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/visits/{visitId}")
    public ResponseEntity<?> getVisit(@PathVariable int visitId) {
        logger.info("Received request for get a visit");
        try {
            return ResponseEntity.ok(visitService.findVisitById(visitId));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("An exception occurred: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/visits")
    public ResponseEntity<?> getAllVisits() {
        logger.info("Received request to get all visits");
        try {
            return ResponseEntity.ok(visitService.findAllVisits());
        } catch (Exception e) {
            logger.error("An exception occurred : {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/visits/nic/{visitorNic}")
    public ResponseEntity<?> getVisitsByNic(@PathVariable String visitorNic) {
        logger.info("Received request to get visits by NIC");
        try {
            return ResponseEntity.ok(visitService.findVisitByVisitorNic(visitorNic));
        } catch (Exception e) {
            logger.error("An exception occurred : {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/visits/overdue")
    public ResponseEntity<?> getOverdueVisits() {
        logger.info("Received request to get overdue visits");
        try {
            return ResponseEntity.ok(visitService.findOverdueVisits());
        } catch (Exception e) {
            logger.error("An exception occurred : {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
