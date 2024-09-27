package com.ceyentra.visitor_management_system.rest;

import com.ceyentra.visitor_management_system.entity.Building;
import com.ceyentra.visitor_management_system.entity.Employee;
import com.ceyentra.visitor_management_system.exception.NotFoundException;
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
    public Employee saveEmployee(@PathVariable int buildingId, @RequestBody Employee employee){
        Building building = employeeService.findBuilding(buildingId);
        if (building != null) {
            employee.setId(0);
            employee.setBuilding(building);
            return employeeService.saveEmployee(employee);
        }else {
            throw new NotFoundException("Building not found with building ID " + buildingId);
        }
    }

    @PutMapping("/employees/{employeeId}")
    public Employee updateEmployee(@PathVariable int employeeId, @RequestBody Employee employee){
        if (employeeService.existsEmployee(employeeId)) {
            employee.setId(employeeId);
            return employeeService.updateEmployee(employee);
        }else {
            throw new NotFoundException("Employee not found with employee ID " + employeeId);
        }
    }

    @DeleteMapping("/employees/{employeeId}")
    public void deleteEmployee(@PathVariable int employeeId){
        if (employeeService.existsEmployee(employeeId)) {
            employeeService.deleteEmployee(employeeId);
        }else {
            throw new NotFoundException("Employee not found with employee ID " + employeeId);
        }

    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){
        if (employeeService.existsEmployee(employeeId)) {
            return employeeService.findEmployeeById(employeeId);
        }else {
            throw new NotFoundException("Employee not found with employee ID " + employeeId);
        }
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeService.findAllVisitors();
    }

    @GetMapping("/employees/active")
    public List<Employee> getAllActiveEmployees(){
        return employeeService.findExistingEmployees();
    }

    @GetMapping("/employees/deactive")
    public List<Employee> getAllDeActiveEmployees(){
        return employeeService.findExistedEmployees();
    }

}
