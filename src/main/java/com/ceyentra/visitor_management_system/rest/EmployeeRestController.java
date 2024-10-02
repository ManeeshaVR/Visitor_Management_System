package com.ceyentra.visitor_management_system.rest;

import com.ceyentra.visitor_management_system.dto.BuildingDTO;
import com.ceyentra.visitor_management_system.dto.EmployeeDTO;
import com.ceyentra.visitor_management_system.entity.Building;
import com.ceyentra.visitor_management_system.service.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    @Autowired
    public  EmployeeRestController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/employees/health")
    public String checkHealth(){
        return "Hello Employee";
    }

    @PostMapping("/employees/{buildingId}")
    public EmployeeDTO saveEmployee(@PathVariable int buildingId, @RequestBody EmployeeDTO employee){
        return employeeService.saveEmployee(employee, buildingId);
    }

    @PutMapping("/employees/{employeeId}")
    public EmployeeDTO updateEmployee(@PathVariable int employeeId, @RequestBody EmployeeDTO employee){
        employee.setId(employeeId);
        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/employees/{employeeId}")
    public void deleteEmployee(@PathVariable int employeeId){
        employeeService.deleteEmployee(employeeId);
    }

    @GetMapping("/employees/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable int employeeId){
        return employeeService.findEmployeeById(employeeId);
    }

    @GetMapping("/employees")
    public List<EmployeeDTO> getAllEmployees(){
        return employeeService.findAllVisitors();
    }

    @GetMapping("/employees/active")
    public List<EmployeeDTO> getAllActiveEmployees(){
        return employeeService.findExistingEmployees();
    }

    @GetMapping("/employees/deactive")
    public List<EmployeeDTO> getAllDeActiveEmployees(){
        return employeeService.findExistedEmployees();
    }

}
