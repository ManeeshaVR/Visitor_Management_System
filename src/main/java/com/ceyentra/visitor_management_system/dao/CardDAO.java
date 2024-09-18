package com.ceyentra.visitor_management_system.dao;

import com.ceyentra.visitor_management_system.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardDAO extends JpaRepository<Card, Integer> {
}
