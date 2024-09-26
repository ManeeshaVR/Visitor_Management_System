package com.ceyentra.visitor_management_system.rest;

import com.ceyentra.visitor_management_system.entity.Employee;
import com.ceyentra.visitor_management_system.service.EmployeeService;
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

    @PostMapping("/employees")
    public Employee saveEmployee(@RequestBody Employee employee){
        employee.setId(0);
        return employeeService.saveEmployee(employee);
    }

    @PutMapping("/employees/{employeeId}")
    public Employee updateEmployee(@PathVariable int employeeId, @RequestBody Employee employee){
        employee.setId(employeeId);
        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/employees/{employeeId}")
    public void deleteEmployee(@PathVariable int employeeId){
        employeeService.deleteEmployee(employeeId);
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){
        return employeeService.findEmployeeById(employeeId);
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
