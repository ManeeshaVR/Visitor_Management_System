package com.ceyentra.visitor_management_system.service;

import com.ceyentra.visitor_management_system.dao.VisitorDAO;
import com.ceyentra.visitor_management_system.entity.Visitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitorServiceImpl implements VisitorService{

    private VisitorDAO visitorDAO;

    @Autowired
    public VisitorServiceImpl(VisitorDAO visitorDAO) {
        this.visitorDAO = visitorDAO;
    }

    @Override
    public void save(Visitor visitor) {

    }

    @Override
    public void update(Visitor visitor) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Visitor findById(Integer id) {
        return null;
    }

    @Override
    public List<Visitor> findAllVisitors() {
        return null;
    }

}
