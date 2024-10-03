package com.ceyentra.visitor_management_system.rest;

import com.ceyentra.visitor_management_system.dto.VisitorDTO;
import com.ceyentra.visitor_management_system.exception.DuplicationException;
import com.ceyentra.visitor_management_system.exception.ResourceNotFoundException;
import com.ceyentra.visitor_management_system.service.VisitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class VisitorRestController {

    private VisitorService visitorService;
    private static final Logger logger = LoggerFactory.getLogger(VisitorRestController.class);

    @Autowired
    public VisitorRestController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @GetMapping("/health")
    public String checkHealth() {
        return "Hello Visitor";
    }

    @PostMapping("/visitors")
    public ResponseEntity<?> saveVisitor(@RequestBody VisitorDTO visitor) {
        logger.info("Received request for save a visitor");
        try {
            visitor.setVisitorId(0);
            logger.info("Request processed successfully");
            return ResponseEntity.ok(visitorService.saveVisitor(visitor));
        } catch (Exception e) {
            logger.error("An exception occurred: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/visitors/{visitorId}")
    public ResponseEntity<?> updateVisitor(@PathVariable int visitorId, @RequestBody VisitorDTO visitor) {
        logger.info("Received request for update a visitor");
        try {

            visitor.setVisitorId(visitorId);
            logger.info("Request processed successfully");
            return ResponseEntity.ok(visitorService.updateVisitor(visitor));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("An exception occurred: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/visitors/{visitorId}")
    public ResponseEntity<?> deleteVisitor(@PathVariable int visitorId) {
        logger.info("Received request for delete a visitor");
        try {
            visitorService.deleteVisitor(visitorId);
            logger.info("Request processed successfully");
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("An exception occurred: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/visitors/{visitorId}")
    public ResponseEntity<?> getVisitor(@PathVariable int visitorId) {
        logger.info("Received request for get a visitor by ID");
        try {
            return ResponseEntity.ok(visitorService.findVisitorById(visitorId));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("An exception occurred: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/visitors/nic/{nic}")
    public ResponseEntity<?> getVisitorByNic(@PathVariable String nic) {
        logger.info("Received request for get a visitor by NIC");
        try {
            return ResponseEntity.ok(visitorService.findVisitorByNic(nic));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("An exception occurred: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/visitors")
    public ResponseEntity<?> getAllVisitors() {
        logger.info("Received request to get all visitors");
        try {
            return ResponseEntity.ok(visitorService.findAllVisitors());
        } catch (Exception e) {
            logger.error("An exception occurred while fetching visitors: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
