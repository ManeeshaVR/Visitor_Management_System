package com.ceyentra.visitor_management_system.service.impl;

import com.ceyentra.visitor_management_system.dao.BuildingDAO;
import com.ceyentra.visitor_management_system.dao.EmployeeDAO;
import com.ceyentra.visitor_management_system.dto.BuildingDTO;
import com.ceyentra.visitor_management_system.dto.EmployeeDTO;
import com.ceyentra.visitor_management_system.entity.Employee;
import com.ceyentra.visitor_management_system.exception.ResourceNotFoundException;
import com.ceyentra.visitor_management_system.service.EmployeeService;
import com.ceyentra.visitor_management_system.util.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDAO, BuildingDAO buildingDAO, Mapper mapper){
        this.employeeDAO = employeeDAO;
        this.buildingDAO = buildingDAO;
        this.mapper = mapper;
    }

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO, Integer buildingId) {
        try {
            BuildingDTO building = findBuilding(buildingId);
            if (building != null) {
                employeeDTO.setId(0);
                employeeDTO.setBuilding(building);
                return mapper.toEmployeeDTO(employeeDAO.save(mapper.toEmployeeEntity(employeeDTO)));
            }else {
                logger.warn("Building with ID {} not found.", buildingId);
                throw new ResourceNotFoundException("Building not found with building ID " + buildingId);
            }
        } catch (DataAccessException e) {
            logger.error("Error saving employee: {}", e.getMessage());
            throw new RuntimeException("Error saving employee data.", e);
        }
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) {
        try {
            Optional<Employee> existingEmployee = employeeDAO.findById(employeeDTO.getId());
            if (existingEmployee.isPresent()) {
                Employee employee = existingEmployee.get();
                employee.setName(employeeDTO.getName());
                employee.setPhoneNo(employeeDTO.getPhoneNo());
                employee.setEmail(employeeDTO.getEmail());
                employee.setPosition(employeeDTO.getPosition());
                employee.setStatus(employeeDTO.getStatus());
                return mapper.toEmployeeDTO(employeeDAO.save(employee));
            } else {
                logger.warn("Employee with ID {} not found.", employeeDTO.getId());
                throw new ResourceNotFoundException("Employee not found for ID: " + employeeDTO.getId());
            }
        } catch (DataAccessException e) {
            logger.error("Error updating employee: {}", e.getMessage());
            throw new RuntimeException("Error updating employee data.", e);
        }
    }

    @Override
    public void deleteEmployee(Integer id) {
        try {
            Optional<Employee> existingEmployee = employeeDAO.findById(id);
            if (existingEmployee.isPresent()) {
                existingEmployee.get().setStatus("DEACTIVE");
                employeeDAO.save(existingEmployee.get()); // Persist the change
            } else {
                logger.warn("Employee with ID {} not found.", id);
                throw new ResourceNotFoundException("Employee not found for ID: " + id);
            }
        } catch (DataAccessException e) {
            logger.error("Error deleting employee: {}", e.getMessage());
            throw new RuntimeException("Error deleting employee data.", e);
        }
        Optional<Employee> existingEmployee = employeeDAO.findById(id);
        existingEmployee.get().setStatus("DEACTIVE");
    }

    @Override
    public EmployeeDTO findEmployeeById(Integer id) {
        try {
            Optional<Employee> employee = employeeDAO.findById(id);
            if (employee.isPresent()) {
                return mapper.toEmployeeDTO(employee.get());
            } else {
                logger.warn("Employee with ID {} not found.", id);
                throw new ResourceNotFoundException("Employee not found for ID: " + id);
            }
        } catch (DataAccessException e) {
            logger.error("Error finding employee by ID: {}", e.getMessage());
            throw new RuntimeException("Error finding employee data.", e);
        }
    }

    @Override
    public List<EmployeeDTO> findAllVisitors() {
        try {
            return mapper.toEmployeeDTOList(employeeDAO.findAll());
        } catch (DataAccessException e) {
            logger.error("Error retrieving all employees: {}", e.getMessage());
            throw new RuntimeException("Error retrieving employees data.", e);
        }
    }

    @Override
    public List<EmployeeDTO> findExistingEmployees() {
        try {
            return mapper.toEmployeeDTOList(employeeDAO.findActiveEmployees());
        } catch (DataAccessException e) {
            logger.error("Error retrieving existing employees: {}", e.getMessage());
            throw new RuntimeException("Error retrieving existing employees data.", e);
        }
    }

    @Override
    public List<EmployeeDTO> findExistedEmployees() {
        try {
            return mapper.toEmployeeDTOList(employeeDAO.findDeActiveEmployees());
        } catch (DataAccessException e) {
            logger.error("Error retrieving existed employees: {}", e.getMessage());
            throw new RuntimeException("Error retrieving existed employees data.", e);
        }
    }

    @Override
    public BuildingDTO findBuilding(Integer id) {
        try {
            return mapper.toBuildingDTO(buildingDAO.getReferenceById(id));
        } catch (DataAccessException e) {
            logger.error("Error finding building: {}", e.getMessage());
            throw new RuntimeException("Error finding building data.", e);
        }
    }

    @Override
    public boolean existsEmployee(Integer id) {
        try {
            return employeeDAO.existsById(id);
        } catch (DataAccessException e) {
            logger.error("Error checking employee existence: {}", e.getMessage());
            throw new RuntimeException("Error checking employee existence", e);
        }
    }

    @Override
    public boolean existsBuilding(Integer id) {
        try {
            return employeeDAO.existsById(id);
        } catch (DataAccessException e) {
            logger.error("Error checking employee existence: {}", e.getMessage());
            throw new RuntimeException("Error checking employee existence", e);
        }
    }
}
