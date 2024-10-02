package com.ceyentra.visitor_management_system.service.impl;

import com.ceyentra.visitor_management_system.dao.BuildingDAO;
import com.ceyentra.visitor_management_system.dao.EmployeeDAO;
import com.ceyentra.visitor_management_system.dto.BuildingDTO;
import com.ceyentra.visitor_management_system.dto.EmployeeDTO;
import com.ceyentra.visitor_management_system.entity.Employee;
import com.ceyentra.visitor_management_system.service.EmployeeService;
import com.ceyentra.visitor_management_system.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDAO employeeDAO;
    private BuildingDAO buildingDAO;
    private final Mapper mapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDAO, BuildingDAO buildingDAO, Mapper mapper){
        this.employeeDAO = employeeDAO;
        this.buildingDAO = buildingDAO;
        this.mapper = mapper;
    }


    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        return mapper.toEmployeeDTO(employeeDAO.save(mapper.toEmployeeEntity(employeeDTO)));
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) {
        Optional<Employee> existingEmployee = employeeDAO.findById(employeeDTO.getId());
        existingEmployee.get().setName(employeeDTO.getName());
        existingEmployee.get().setPhoneNo(employeeDTO.getPhoneNo());
        existingEmployee.get().setEmail(employeeDTO.getEmail());
        existingEmployee.get().setPosition(employeeDTO.getPosition());
        existingEmployee.get().setStatus(employeeDTO.getStatus());

        return mapper.toEmployeeDTO(employeeDAO.getReferenceById(employeeDTO.getId()));
    }

    @Override
    public void deleteEmployee(Integer id) {
        Optional<Employee> existingEmployee = employeeDAO.findById(id);
        existingEmployee.get().setStatus("DEACTIVE");
    }

    @Override
    public EmployeeDTO findEmployeeById(Integer id) {
        return mapper.toEmployeeDTO(employeeDAO.getReferenceById(id));
    }

    @Override
    public List<EmployeeDTO> findAllVisitors() {
        return mapper.toEmployeeDTOList(employeeDAO.findAll());
    }

    @Override
    public List<EmployeeDTO> findExistingEmployees() {
        return mapper.toEmployeeDTOList(employeeDAO.findActiveEmployees());
    }

    @Override
    public List<EmployeeDTO> findExistedEmployees() {
        return mapper.toEmployeeDTOList(employeeDAO.findDeActiveEmployees());
    }

    @Override
    public BuildingDTO findBuilding(Integer id) {
        return mapper.toBuildingDTO(buildingDAO.getReferenceById(id));
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
