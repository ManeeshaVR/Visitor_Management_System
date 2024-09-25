package com.ceyentra.visitor_management_system.service.impl;

import com.ceyentra.visitor_management_system.dao.VisitorDAO;
import com.ceyentra.visitor_management_system.entity.Visitor;
import com.ceyentra.visitor_management_system.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VisitorServiceImpl implements VisitorService {

    private VisitorDAO visitorDAO;

    @Autowired
    public VisitorServiceImpl(VisitorDAO visitorDAO) {
        this.visitorDAO = visitorDAO;
    }

    @Override
    public Visitor saveVisitor(Visitor visitor) {
        return visitorDAO.save(visitor);
    }

    @Override
    public Visitor updateVisitor(Visitor visitor) {
        Optional<Visitor> tempVisitor = visitorDAO.findById(visitor.getVisitorId());
        tempVisitor.get().setName(visitor.getName());
        tempVisitor.get().setEmail(visitor.getEmail());
        tempVisitor.get().setNic(visitor.getNic());
        tempVisitor.get().setPhoneNo(visitor.getPhoneNo());

        return visitorDAO.getReferenceById(visitor.getVisitorId());
    }

    @Override
    public void deleteVisitor(Integer id) {
        visitorDAO.deleteById(id);
    }

    @Override
    public Visitor findVisitorById(Integer id) {
        return visitorDAO.getReferenceById(id);
    }

    @Override
    public Visitor findVisitorByNic(String nic) {
        return visitorDAO.findByNic(nic);
    }

    @Override
    public List<Visitor> findAllVisitors() {
        return visitorDAO.findAll();
    }

}
