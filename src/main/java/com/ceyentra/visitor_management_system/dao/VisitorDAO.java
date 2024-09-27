package com.ceyentra.visitor_management_system.dao;

import com.ceyentra.visitor_management_system.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorDAO extends JpaRepository<Visitor, Integer> {

    Visitor findByNic(String nic);

    boolean existsByVisitorId(Integer id);

}
