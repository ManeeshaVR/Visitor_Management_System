package com.ceyentra.visitor_management_system.service;

import com.ceyentra.visitor_management_system.entity.Building;
import com.ceyentra.visitor_management_system.entity.Employee;
import com.ceyentra.visitor_management_system.entity.Visitor;

import java.util.List;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    void deleteEmployee(Integer id);

    Employee findEmployeeById(Integer id);

    List<Employee> findAllVisitors();

    List<Employee> findExistingEmployees();

    List<Employee> findExistedEmployees();

    Building findBuilding(Integer id);

}
