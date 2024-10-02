package com.ceyentra.visitor_management_system.service.impl;

import com.ceyentra.visitor_management_system.dao.VisitorDAO;
import com.ceyentra.visitor_management_system.dto.VisitorDTO;
import com.ceyentra.visitor_management_system.entity.Visitor;
import com.ceyentra.visitor_management_system.service.VisitorService;
import com.ceyentra.visitor_management_system.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VisitorServiceImpl implements VisitorService {

    private VisitorDAO visitorDAO;
    private final Mapper mapper;

    @Autowired
    public VisitorServiceImpl(VisitorDAO visitorDAO, Mapper mapper) {
        this.visitorDAO = visitorDAO;
        this.mapper = mapper;
    }

    @Override
    public VisitorDTO saveVisitor(VisitorDTO visitorDTO) {
        return mapper.toVisitorDTO(visitorDAO.save(mapper.toVisitorEntity(visitorDTO)));
    }

    @Override
    public VisitorDTO updateVisitor(VisitorDTO visitorDTO) {
        Optional<Visitor> tempVisitor = visitorDAO.findById(visitorDTO.getVisitorId());
        tempVisitor.get().setName(visitorDTO.getName());
        tempVisitor.get().setEmail(visitorDTO.getEmail());
        tempVisitor.get().setNic(visitorDTO.getNic());
        tempVisitor.get().setPhoneNo(visitorDTO.getPhoneNo());

        return mapper.toVisitorDTO(visitorDAO.getReferenceById(visitorDTO.getVisitorId()));
    }

    @Override
    public void deleteVisitor(Integer id) {
        visitorDAO.deleteById(id);
    }

    @Override
    public VisitorDTO findVisitorById(Integer id) {
        return mapper.toVisitorDTO(visitorDAO.getReferenceById(id));
    }

    @Override
    public VisitorDTO findVisitorByNic(String nic) {
        return mapper.toVisitorDTO(visitorDAO.findByNic(nic));
    }

    @Override
    public List<VisitorDTO> findAllVisitors() {
        return mapper.toVisitorDTOList(visitorDAO.findAll());
    }

    @Override
    public boolean existsVisitor(Integer id) {
        return visitorDAO.existsByVisitorId(id);
    }

}
