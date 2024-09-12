package com.ceyentra.visitor_management_system.dao;

import com.ceyentra.visitor_management_system.entity.Visitor;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class VisitorDAOImpl implements VisitorDAO{

    private EntityManager entityManager;

    @Autowired
    public VisitorDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Visitor visitor) {

    }

    @Override
    public Visitor findById(Integer id) {
        return null;
    }

    @Override
    public List<Visitor> findAllVisitors() {
        return null;
    }

    @Override
    public void update(Visitor visitor) {

    }

    @Override
    public void delete(Integer id) {

    }
}
