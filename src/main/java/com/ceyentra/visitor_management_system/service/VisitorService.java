package com.ceyentra.visitor_management_system.service;

import com.ceyentra.visitor_management_system.entity.Visitor;

import java.util.List;

public interface VisitorService {

    Visitor saveVisitor(Visitor visitor);

    Visitor updateVisitor(Visitor visitor);

    void deleteVisitor(Integer id);

    Visitor findVisitorById(Integer id);

    Visitor findVisitorByNic(String nic);

    List<Visitor> findAllVisitors();

}
