package com.ceyentra.visitor_management_system.service.impl;

import com.ceyentra.visitor_management_system.dao.CardDAO;
import com.ceyentra.visitor_management_system.dao.EmployeeDAO;
import com.ceyentra.visitor_management_system.dao.VisitDAO;
import com.ceyentra.visitor_management_system.dao.VisitorDAO;
import com.ceyentra.visitor_management_system.dto.CardDTO;
import com.ceyentra.visitor_management_system.dto.EmployeeDTO;
import com.ceyentra.visitor_management_system.dto.VisitDTO;
import com.ceyentra.visitor_management_system.dto.VisitorDTO;
import com.ceyentra.visitor_management_system.entity.Card;
import com.ceyentra.visitor_management_system.entity.Employee;
import com.ceyentra.visitor_management_system.entity.Visit;
import com.ceyentra.visitor_management_system.entity.Visitor;
import com.ceyentra.visitor_management_system.exception.ResourceNotFoundException;
import com.ceyentra.visitor_management_system.service.VisitService;
import com.ceyentra.visitor_management_system.util.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VisitServiceImpl implements VisitService {

    private VisitDAO visitDAO;
    private VisitorDAO visitorDAO;
    private CardDAO cardDAO;
    private EmployeeDAO employeeDAO;
    private final Mapper mapper;
    private static final Logger logger = LoggerFactory.getLogger(VisitServiceImpl.class);

    @Autowired
    public VisitServiceImpl(VisitDAO visitDAO, VisitorDAO visitorDAO, CardDAO cardDAO, EmployeeDAO employeeDAO, Mapper mapper) {
        this.visitDAO=visitDAO;
        this.visitorDAO = visitorDAO;
        this.cardDAO = cardDAO;
        this.employeeDAO = employeeDAO;
        this.mapper = mapper;
    }

    @Override
    public VisitDTO saveVisit(VisitDTO visitDTO, Integer employeeId, Integer visitorId) {
        try {
            CardDTO availableCard = findAvailableCard();
            EmployeeDTO employee = findEmployee(employeeId);
            VisitorDTO visitor = findVisitor(visitorId);
            if (availableCard == null){
                throw new ResourceNotFoundException("All available cards have been assigned to visitors.");
            }else if(employee == null){
                throw new ResourceNotFoundException("Employee not found with employee ID : " + employeeId);
            }else if(visitor == null){
                throw new ResourceNotFoundException("Visitor not found with visitor ID : " + visitorId);
            }else {
               availableCard.setStatus("Not Available");
                visitDTO.setVisitor(visitor);
                visitDTO.setEmployee(employee);
                visitDTO.setCard(availableCard);
                visitDTO.setVisitDate(LocalDate.now());
                visitDTO.setCheckIn(LocalTime.now());
                return mapper.toVisitDTO(visitDAO.save(mapper.toVisitEntity(visitDTO)));
            }
        } catch (DataAccessException e) {
            logger.error("Error saving visit: {}", e.getMessage());
            throw new RuntimeException("Error saving visit data.", e);
        }
    }

    @Override
    public VisitDTO updateVisit(Integer visitId) {
        try {
            Optional<Visit> tempVisit = visitDAO.findById(visitId);
            if (tempVisit.isPresent()) {
                Visit visit = tempVisit.get();
                visit.setCheckOut(LocalTime.now());

                // Update card status
                Optional<Card> tempCard = cardDAO.findById(visit.getCard().getCardId());
                    Card card = tempCard.get();
                    card.setStatus("Available");

                return mapper.toVisitDTO(visitDAO.save(visit));
            } else {
                logger.warn("Visit with ID {} not found.", visitId);
                throw new ResourceNotFoundException("Visit not found with ID " + visitId);
            }
        } catch (DataAccessException e) {
            logger.error("Error updating visit: {}", e.getMessage());
            throw new RuntimeException("Error updating visit data.", e);
        }
    }

    @Override
    public void deleteVisit(Integer id) {
        try {
            Optional<Visit> tempVisit = visitDAO.findById(id);
            if (tempVisit.isPresent()) {
                visitDAO.delete(tempVisit.get());
            } else {
                logger.warn("Visit with ID {} not found.", id);
                throw new ResourceNotFoundException("Visit not found for ID: " + id);
            }
        } catch (DataAccessException e) {
            logger.error("Error deleting visit: {}", e.getMessage());
            throw new RuntimeException("Error deleting visit data.", e);
        }
    }

    @Override
    public VisitDTO findVisitById(Integer id) {
        try {
            Optional<Visit> tempVisit = visitDAO.findById(id);
            if (tempVisit.isPresent()) {
                return mapper.toVisitDTO(tempVisit.get());
            } else {
                logger.warn("Visit with ID {} not found.", id);
                throw new ResourceNotFoundException("Visit not found for ID: " + id);
            }
        } catch (DataAccessException e) {
            logger.error("Error finding visit by ID: {}", e.getMessage());
            throw new RuntimeException("Error finding visit data.", e);
        }
    }

    @Override
    public List<VisitDTO> findAllVisits() {
        try {
            return mapper.toVisitDTOList(visitDAO.findAll());
        } catch (DataAccessException e) {
            logger.error("Error retrieving all visits: {}", e.getMessage());
            throw new RuntimeException("Error retrieving visits data.", e);
        }
    }

    @Override
    public List<VisitDTO> findVisitByVisitorNic(String nic) {
        try {
            return mapper.toVisitDTOList(visitDAO.findVisitsByVisitorNic(nic));
        } catch (DataAccessException e) {
            logger.error("Error finding visits by NIC: {}", e.getMessage());
            throw new RuntimeException("Error finding visits by visitor NIC.", e);
        }
    }

    @Override
    public List<VisitDTO> findOverdueVisits() {
        try {
            return mapper.toVisitDTOList(visitDAO.findOverdueVisits());
        } catch (DataAccessException e) {
            logger.error("Error retrieving overdue visits: {}", e.getMessage());
            throw new RuntimeException("Error retrieving overdue visits.", e);
        }
    }

    @Override
    public VisitorDTO findVisitor(Integer id) {
        try {
            return mapper.toVisitorDTO(visitorDAO.getReferenceById(id));
        } catch (DataAccessException e) {
            logger.error("Error finding visitor by ID: {}", e.getMessage());
            throw new RuntimeException("Error finding visitor data.", e);
        }
    }

    @Override
    public CardDTO findAvailableCard() {
        try {
            return mapper.toCardDTO(cardDAO.findAvailableCard());
        } catch (DataAccessException e) {
            logger.error("Error finding available card: {}", e.getMessage());
            throw new RuntimeException("Error finding available card.", e);
        }
    }

    @Override
    public EmployeeDTO findEmployee(Integer id) {
        return mapper.toEmployeeDTO(employeeDAO.getReferenceById(id));
    }

    @Override
    public boolean existsVisit(Integer id) {
        try {
            return visitDAO.existsById(id);
        } catch (DataAccessException e) {
            logger.error("Error checking visit existence: {}", e.getMessage());
            throw new RuntimeException("Error checking visit existence.", e);
        }
    }

    @Override
    public boolean existsVisitor(Integer id) {
        try {
            return visitorDAO.existsById(id);
        } catch (DataAccessException e) {
            logger.error("Error checking visitor existence: {}", e.getMessage());
            throw new RuntimeException("Error checking visitor existence.", e);
        }
    }

    @Override
    public boolean existsEmployee(Integer id) {
        try {
            return employeeDAO.existsById(id);
        } catch (DataAccessException e) {
            logger.error("Error checking employee existence: {}", e.getMessage());
            throw new RuntimeException("Error checking employee existence.", e);
        }
    }

}
