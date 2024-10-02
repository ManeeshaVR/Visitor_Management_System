package com.ceyentra.visitor_management_system.service;

import com.ceyentra.visitor_management_system.dto.BuildingDTO;
import com.ceyentra.visitor_management_system.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

    EmployeeDTO saveEmployee(EmployeeDTO employee);

    EmployeeDTO updateEmployee(EmployeeDTO employee);

    void deleteEmployee(Integer id);

    EmployeeDTO findEmployeeById(Integer id);

    List<EmployeeDTO> findAllVisitors();

    List<EmployeeDTO> findExistingEmployees();

    List<EmployeeDTO> findExistedEmployees();

    BuildingDTO findBuilding(Integer id);

    boolean existsEmployee(Integer id);

    boolean existsBuilding(Integer id);

}
