package com.ceyentra.visitor_management_system.dao;

import com.ceyentra.visitor_management_system.entity.User;
import com.ceyentra.visitor_management_system.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {

    User findByUserName(String userName);

}
