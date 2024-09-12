package com.ceyentra.visitor_management_system.service;

import com.ceyentra.visitor_management_system.entity.Visitor;

import java.util.List;

public interface VisitorService {

    void save(Visitor visitor);

    void update(Visitor visitor);

    void delete(Integer id);

    Visitor findById(Integer id);

    List<Visitor> findAllVisitors();

}
