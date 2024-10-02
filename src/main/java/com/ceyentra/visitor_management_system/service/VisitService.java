package com.ceyentra.visitor_management_system.service;

import com.ceyentra.visitor_management_system.dto.CardDTO;
import com.ceyentra.visitor_management_system.dto.EmployeeDTO;
import com.ceyentra.visitor_management_system.dto.VisitDTO;
import com.ceyentra.visitor_management_system.dto.VisitorDTO;
import com.ceyentra.visitor_management_system.entity.Card;
import com.ceyentra.visitor_management_system.entity.Employee;
import com.ceyentra.visitor_management_system.entity.Visit;
import com.ceyentra.visitor_management_system.entity.Visitor;

import java.util.List;

public interface VisitService {

    VisitDTO saveVisit(VisitDTO visitDTO);

    VisitDTO updateVisit(VisitDTO visitDTO);

    void deleteVisit(Integer id);

    VisitDTO findVisitById(Integer id);

    List<VisitDTO> findAllVisits();

    List<VisitDTO> findVisitByVisitorNic(String nic);

    List<VisitDTO> findOverdueVisits();

    VisitorDTO findVisitor(Integer id);

    CardDTO findAvailableCard();

    EmployeeDTO findEmployee(Integer id);

    boolean existsVisit(Integer id);

    boolean existsVisitor(Integer id);

    boolean existsEmployee(Integer id);

}
