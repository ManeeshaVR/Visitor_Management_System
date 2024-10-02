package com.ceyentra.visitor_management_system.service;

import com.ceyentra.visitor_management_system.dto.VisitorDTO;
import com.ceyentra.visitor_management_system.entity.Visitor;

import java.util.List;

public interface VisitorService {

    VisitorDTO saveVisitor(VisitorDTO visitorDTO);

    VisitorDTO updateVisitor(VisitorDTO visitorDTO);

    void deleteVisitor(Integer id);

    VisitorDTO findVisitorById(Integer id);

    VisitorDTO findVisitorByNic(String nic);

    List<VisitorDTO> findAllVisitors();

    boolean existsVisitor(Integer id);

}
