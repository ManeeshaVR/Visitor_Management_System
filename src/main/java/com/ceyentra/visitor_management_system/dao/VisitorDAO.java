package com.ceyentra.visitor_management_system.dao;

import com.ceyentra.visitor_management_system.entity.Visitor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitorDAO {

    void save(Visitor visitor);

    Visitor findById(Integer id);

    List<Visitor> findAllVisitors();

    void update(Visitor visitor);

    void delete(Integer id);

}
