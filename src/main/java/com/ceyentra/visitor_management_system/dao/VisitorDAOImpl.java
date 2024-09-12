package com.ceyentra.visitor_management_system.dao;

import com.ceyentra.visitor_management_system.entity.Visitor;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class VisitorDAOImpl implements VisitorDAO{

    private EntityManager entityManager;

    @Autowired
    public VisitorDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Visitor visitor) {
        entityManager.persist(visitor);
    }

    @Override
    public Visitor findById(Integer id) {
        return entityManager.find(Visitor.class, id);
    }

    @Override
    public void update(Visitor visitor) {
        entityManager.merge(visitor);
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Visitor> findAllVisitors() {
        return null;
    }

}
