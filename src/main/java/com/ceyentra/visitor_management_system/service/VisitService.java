package com.ceyentra.visitor_management_system.service;

import com.ceyentra.visitor_management_system.entity.Card;
import com.ceyentra.visitor_management_system.entity.Employee;
import com.ceyentra.visitor_management_system.entity.Visit;
import com.ceyentra.visitor_management_system.entity.Visitor;

import java.util.List;

public interface VisitService {

    Visit saveVisit(Visit visit);

    Visit updateVisit(Visit visit);

    void deleteVisit(Integer id);

    Visit findVisitById(Integer id);

    List<Visit> findAllVisits();

    List<Visit> findVisitByVisitorNic(String nic);

    List<Visit> findOverdueVisits();

    Visitor findVisitor(Integer id);

    Card findAvailableCard();

    Employee findEmployee(Integer id);

    boolean existsVisit(Integer id);

    boolean existsVisitor(Integer id);

    boolean existsEmployee(Integer id);

}
