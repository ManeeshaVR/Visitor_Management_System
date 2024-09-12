package com.ceyentra.visitor_management_system.service;

import com.ceyentra.visitor_management_system.entity.Visit;
import com.ceyentra.visitor_management_system.entity.Visitor;

import java.util.List;

public interface VisitService {

    Visit save(Visit visit);

    Visit findById(Integer id);

    List<Visit> findAllVisits();

    Visit update(Visit visit);

    void delete(Integer id);

    Visitor findVisitor(Integer id);

}
