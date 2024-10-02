package com.ceyentra.visitor_management_system.rest;

import com.ceyentra.visitor_management_system.dto.VisitorDTO;
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
    public String checkHealth() {
        return "Hello Visitor";
    }

    @PostMapping("/visitors")
    public VisitorDTO saveVisitor(@RequestBody VisitorDTO visitor) {
        visitor.setVisitorId(0);
        return visitorService.saveVisitor(visitor);
    }

    @PutMapping("/visitors/{visitorId}")
    public VisitorDTO updateVisitor(@PathVariable int visitorId, @RequestBody VisitorDTO visitor) {
        visitor.setVisitorId(visitorId);
        return visitorService.updateVisitor(visitor);
    }

    @DeleteMapping("/visitors/{visitorId}")
    public void deleteVisitor(@PathVariable int visitorId) {
        visitorService.deleteVisitor(visitorId);

    }

    @GetMapping("/visitors/{visitorId}")
    public VisitorDTO getVisitor(@PathVariable int visitorId) {
            return visitorService.findVisitorById(visitorId);
    }

    @GetMapping("/visitors/nic/{nic}")
    public VisitorDTO getVisitorByNic(@PathVariable String nic) {
        return visitorService.findVisitorByNic(nic);
    }

    @GetMapping("/visitors")
    public List<VisitorDTO> getAllVisitors() {
        return visitorService.findAllVisitors();
    }

}
