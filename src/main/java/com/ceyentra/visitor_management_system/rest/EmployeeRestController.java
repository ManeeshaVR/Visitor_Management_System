package com.ceyentra.visitor_management_system.rest;

import com.ceyentra.visitor_management_system.dto.BuildingDTO;
import com.ceyentra.visitor_management_system.dto.EmployeeDTO;
import com.ceyentra.visitor_management_system.entity.Building;
import com.ceyentra.visitor_management_system.exception.ResourceNotFoundException;
import com.ceyentra.visitor_management_system.service.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;
    private static final Logger logger = LoggerFactory.getLogger(EmployeeRestController.class);

    @Autowired
    public  EmployeeRestController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/employees/health")
    public String checkHealth(){
        return "Hello Employee";
    }

    @PostMapping("/employees/{buildingId}")
    public ResponseEntity<?> saveEmployee(@PathVariable int buildingId, @RequestBody EmployeeDTO employee){
        logger.info("Received request for save a Employee");
        try {
            return ResponseEntity.ok(employeeService.saveEmployee(employee, buildingId));
        } catch (Exception e) {
            logger.error("An exception occurred: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/employees/{employeeId}")
    public ResponseEntity<?> updateEmployee(@PathVariable int employeeId, @RequestBody EmployeeDTO employee){
        logger.info("Received request for update a Employee");
        try {
            employee.setId(employeeId);
            return ResponseEntity.ok(employeeService.updateEmployee(employee));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("An exception occurred: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }


    }

    @DeleteMapping("/employees/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable int employeeId){
        logger.info("Received request for delete a visitor");
        try {
            employeeService.deleteEmployee(employeeId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("An exception occurred: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<?> getEmployee(@PathVariable int employeeId){
        logger.info("Received request for get a Employee");
        try {
            return ResponseEntity.ok(employeeService.findEmployeeById(employeeId));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("An exception occurred: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/employees")
    public ResponseEntity<?> getAllEmployees(){
        logger.info("Received request to get all employees");
        try {
            return ResponseEntity.ok(employeeService.findAllVisitors());
        } catch (Exception e) {
            logger.error("An exception occurred while fetching employees: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/employees/active")
    public ResponseEntity<?> getAllActiveEmployees(){
        logger.info("Received request to get all active employees");
        try {
            return ResponseEntity.ok(employeeService.findExistingEmployees());
        } catch (Exception e) {
            logger.error("An exception occurred while fetching active employees: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/employees/deactive")
    public ResponseEntity<?> getAllDeActiveEmployees(){
        logger.info("Received request to get all active employees");
        try {
            return ResponseEntity.ok(employeeService.findExistedEmployees());
        } catch (Exception e) {
            logger.error("An exception occurred while fetching de active employees: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
