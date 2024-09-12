package com.ceyentra.visitor_management_system.dao;

import com.ceyentra.visitor_management_system.entity.Visitor;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface VisitorDAO {

    Visitor save(Visitor visitor);

    Visitor findById(Integer id);

    List<Visitor> findAllVisitors();

    Visitor update(Visitor visitor);

    void delete(Integer id);

}
