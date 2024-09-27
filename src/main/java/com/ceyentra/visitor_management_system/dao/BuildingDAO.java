package com.ceyentra.visitor_management_system.dao;

import com.ceyentra.visitor_management_system.entity.Building;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingDAO extends JpaRepository<Building, Integer> {

    boolean existsByBuildingId(Integer id);

}
