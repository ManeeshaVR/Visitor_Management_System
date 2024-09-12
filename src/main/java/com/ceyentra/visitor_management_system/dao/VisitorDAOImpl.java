package com.ceyentra.visitor_management_system.dao;

import com.ceyentra.visitor_management_system.entity.Visitor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
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
    @Transactional
    public void update(Visitor visitor) {
        entityManager.merge(visitor);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Visitor visitor = entityManager.find(Visitor.class, id);
        entityManager.remove(visitor);
    }

    @Override
    public Visitor findById(Integer id) {
        return entityManager.find(Visitor.class, id);
    }

    @Override
    public List<Visitor> findAllVisitors() {
        TypedQuery<Visitor> theQuery = entityManager.createQuery("FROM Visitor", Visitor.class);
        List<Visitor> visitors = theQuery.getResultList();
        return visitors;
    }

}
