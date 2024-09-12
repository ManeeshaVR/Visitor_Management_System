package com.ceyentra.visitor_management_system.service;

import com.ceyentra.visitor_management_system.dao.VisitDAO;
import com.ceyentra.visitor_management_system.dao.VisitorDAO;
import com.ceyentra.visitor_management_system.entity.Visit;
import com.ceyentra.visitor_management_system.entity.Visitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitServiceImpl implements VisitService{

    private VisitDAO visitDAO;
    private VisitorDAO visitorDAO;

    @Autowired
    public VisitServiceImpl(VisitDAO visitDAO, VisitorDAO visitorDAO) {
        this.visitDAO=visitDAO;
        this.visitorDAO = visitorDAO;
    }

    @Override
    public Visit save(Visit visit) {
        return null;
    }

    @Override
    public Visit findById(Integer id) {
        return null;
    }

    @Override
    public List<Visit> findAllVisits() {
        return null;
    }

    @Override
    public Visit update(Visit visit) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Visitor findVisitor(Integer id) {
        return null;
    }
}
