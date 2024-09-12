package com.ceyentra.visitor_management_system.service;

import com.ceyentra.visitor_management_system.dao.VisitDAO;
import com.ceyentra.visitor_management_system.dao.VisitorDAO;
import com.ceyentra.visitor_management_system.entity.Visit;
import com.ceyentra.visitor_management_system.entity.Visitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Override
    public Visit save(Visit visit) {
        return visitDAO.save(visit);
    }

    @Transactional
    @Override
    public Visit update(Visit visit) {
        return visitDAO.update(visit);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        visitDAO.delete(id);
    }

    @Override
    public Visit findById(Integer id) {
        return visitDAO.findById(id);
    }

    @Override
    public List<Visit> findAllVisits() {
        return visitDAO.findAllVisits();
    }

    @Override
    public Visitor findVisitor(Integer id) {
        return visitorDAO.findById(id);
    }

}
