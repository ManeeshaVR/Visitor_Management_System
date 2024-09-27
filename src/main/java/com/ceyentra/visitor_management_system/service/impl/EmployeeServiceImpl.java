package com.ceyentra.visitor_management_system.service.impl;

import com.ceyentra.visitor_management_system.dao.BuildingDAO;
import com.ceyentra.visitor_management_system.dao.EmployeeDAO;
import com.ceyentra.visitor_management_system.entity.Building;
import com.ceyentra.visitor_management_system.entity.Employee;
import com.ceyentra.visitor_management_system.entity.Visitor;
import com.ceyentra.visitor_management_system.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDAO employeeDAO;
    private BuildingDAO buildingDAO;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDAO, BuildingDAO buildingDAO){
        this.employeeDAO = employeeDAO;
        this.buildingDAO = buildingDAO;
    }


    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeDAO.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        Optional<Employee> existingEmployee = employeeDAO.findById(employee.getId());
        existingEmployee.get().setName(employee.getName());
        existingEmployee.get().setPhoneNo(employee.getPhoneNo());
        existingEmployee.get().setEmail(employee.getEmail());
        existingEmployee.get().setPosition(employee.getPosition());
        existingEmployee.get().setStatus(employee.getStatus());

        return employeeDAO.getReferenceById(employee.getId());
    }

    @Override
    public void deleteEmployee(Integer id) {
        Optional<Employee> existingEmployee = employeeDAO.findById(id);
        existingEmployee.get().setStatus("DEACTIVE");
    }

    @Override
    public Employee findEmployeeById(Integer id) {
        return employeeDAO.getReferenceById(id);
    }

    @Override
    public List<Employee> findAllVisitors() {
        return employeeDAO.findAll();
    }

    @Override
    public List<Employee> findExistingEmployees() {
        return employeeDAO.findActiveEmployees();
    }

    @Override
    public List<Employee> findExistedEmployees() {
        return employeeDAO.findDeActiveEmployees();
    }

    @Override
    public Building findBuilding(Integer id) {
        return buildingDAO.getReferenceById(id);
    }

    @Override
    public boolean existsEmployee(Integer id) {
        return employeeDAO.existsById(id);
    }

    @Override
    public boolean existsBuilding(Integer id) {
        return buildingDAO.existsByBuildingId(id);
    }
}
