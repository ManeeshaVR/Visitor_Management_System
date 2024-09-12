package com.ceyentra.visitor_management_system.dao;

import com.ceyentra.visitor_management_system.entity.Visit;
import com.ceyentra.visitor_management_system.entity.Visitor;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VisitDAOImpl implements VisitDAO{

    private EntityManager entityManager;

    @Autowired
    public VisitDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Visit save(Visit visit) {
        return entityManager.merge(visit);
    }

    @Override
    public Visit update(Visit visit) {
        return entityManager.merge(visit);
    }

    @Override
    public void delete(Integer id) {
        Visit visit = entityManager.find(Visit.class, id);
        entityManager.remove(visit);
    }

    @Override
    public Visit findById(Integer id) {
        return entityManager.find(Visit.class, id);
    }

    @Override
    public List<Visit> findAllVisits() {
        return null;
    }

}
