package com.ceyentra.visitor_management_system.dao;

import com.ceyentra.visitor_management_system.entity.Visit;
import com.ceyentra.visitor_management_system.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitDAO extends JpaRepository<Visit, Integer> {

    @Query("SELECT v FROM Visit v WHERE v.visitor.nic = :nic")
    List<Visit> findVisitsByVisitorNic(@Param("nic") String nic);

    @Query("SELECT v FROM Visit v WHERE v.checkOut IS NULL AND v.visitDate != CURRENT_DATE")
    List<Visit> findOverdueVisits();

}
