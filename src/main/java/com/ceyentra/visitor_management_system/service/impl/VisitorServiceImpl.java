package com.ceyentra.visitor_management_system.service.impl;

import com.ceyentra.visitor_management_system.dao.VisitorDAO;
import com.ceyentra.visitor_management_system.dto.VisitorDTO;
import com.ceyentra.visitor_management_system.entity.Visitor;
import com.ceyentra.visitor_management_system.exception.DuplicationException;
import com.ceyentra.visitor_management_system.exception.ResourceNotFoundException;
import com.ceyentra.visitor_management_system.service.VisitorService;
import com.ceyentra.visitor_management_system.util.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VisitorServiceImpl implements VisitorService {

    private VisitorDAO visitorDAO;
    private final Mapper mapper;
    private static final Logger logger = LoggerFactory.getLogger(VisitorServiceImpl.class);

    @Autowired
    public VisitorServiceImpl(VisitorDAO visitorDAO, Mapper mapper) {
        this.visitorDAO = visitorDAO;
        this.mapper = mapper;
    }

    @Override
    public VisitorDTO saveVisitor(VisitorDTO visitorDTO) {
        try {
            if (!existsVisitor(visitorDTO.getVisitorId())){
                return mapper.toVisitorDTO(visitorDAO.save(mapper.toVisitorEntity(visitorDTO)));
            }else {
                logger.warn("A visitor already exists with visitor ID : "+visitorDTO.getVisitorId());
                throw new DuplicationException();
            }
        } catch (DataAccessException e) {
            logger.error("Error saving visitor: {}", e.getMessage());
            throw new RuntimeException("Error saving visitor data.", e);
        }
    }

    @Override
    public VisitorDTO updateVisitor(VisitorDTO visitorDTO) {
        try {
            Optional<Visitor> tempVisitor = visitorDAO.findById(visitorDTO.getVisitorId());
            if (tempVisitor.isPresent()) {
                tempVisitor.get().setName(visitorDTO.getName());
                tempVisitor.get().setEmail(visitorDTO.getEmail());
                tempVisitor.get().setNic(visitorDTO.getNic());
                tempVisitor.get().setPhoneNo(visitorDTO.getPhoneNo());

                return mapper.toVisitorDTO(visitorDAO.getReferenceById(visitorDTO.getVisitorId()));
            } else {
                logger.warn("Visitor with ID {} not found.", visitorDTO.getVisitorId());
                throw new ResourceNotFoundException("Visitor not found for ID: " + visitorDTO.getVisitorId());
            }
        } catch (DataAccessException e) {
            logger.error("Error updating visitor: {}", e.getMessage());
            throw new RuntimeException("Error updating visitor data", e);
        }
    }

    @Override
    public void deleteVisitor(Integer id) {
        try {
            if (visitorDAO.existsById(id)) {
                visitorDAO.deleteById(id);
            } else {
                logger.warn("Visitor with ID {} not found.", id);
                throw new ResourceNotFoundException("Visitor not found for ID: " + id);
            }
        } catch (DataAccessException e) {
            logger.error("Error deleting visitor: {}", e.getMessage());
            throw new RuntimeException("Error deleting visitor data", e);
        }
    }

    @Override
    public VisitorDTO findVisitorById(Integer id) {
        try {
            Optional<Visitor> visitor = visitorDAO.findById(id);
            if (visitor.isPresent()) {
                return mapper.toVisitorDTO(visitor.get());
            } else {
                logger.warn("Visitor with ID {} not found.", id);
                throw new ResourceNotFoundException("Visitor not found for ID: " + id);
            }
        } catch (DataAccessException e) {
            logger.error("Error finding visitor by ID: {}", e.getMessage());
            throw new RuntimeException("Error finding visitor data", e);
        }
    }

    @Override
    public VisitorDTO findVisitorByNic(String nic) {
        try {
            Visitor visitor = visitorDAO.findByNic(nic);
            if (visitor != null) {
                return mapper.toVisitorDTO(visitor);
            } else {
                logger.warn("Visitor with NIC {} not found.", nic);
                throw new ResourceNotFoundException("Visitor not found for NIC: " + nic);
            }
        } catch (DataAccessException e) {
            logger.error("Error finding visitor by NIC: {}", e.getMessage());
            throw new RuntimeException("Error finding visitor data", e);
        }
    }

    @Override
    public List<VisitorDTO> findAllVisitors() {
        try {
            return mapper.toVisitorDTOList(visitorDAO.findAll());
        } catch (DataAccessException e) {
            logger.error("Error retrieving all visitors: {}", e.getMessage());
            throw new RuntimeException("Error retrieving visitors data", e);
        }
    }

    @Override
    public boolean existsVisitor(Integer id) {
        try {
            return visitorDAO.existsByVisitorId(id);
        } catch (DataAccessException e) {
            logger.error("Error checking visitor existence: {}", e.getMessage());
            throw new RuntimeException("Error checking visitor existence", e);
        }
    }

}
