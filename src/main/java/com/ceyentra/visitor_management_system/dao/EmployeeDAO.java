package com.ceyentra.visitor_management_system.dao;

import com.ceyentra.visitor_management_system.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDAO extends JpaRepository<Employee, Integer> {

    @Query("SELECT e FROM Employee e WHERE e.status = 'ACTIVE'")
    List<Employee> findActiveEmployees();

    @Query("SELECT e FROM Employee e WHERE e.status = 'DEACTIVE'")
    List<Employee> findDeActiveEmployees();

    boolean existsById(Integer id);

}
