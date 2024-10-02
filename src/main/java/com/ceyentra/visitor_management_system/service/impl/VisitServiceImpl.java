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
import com.ceyentra.visitor_management_system.service.VisitService;
import com.ceyentra.visitor_management_system.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    public VisitServiceImpl(VisitDAO visitDAO, VisitorDAO visitorDAO, CardDAO cardDAO, EmployeeDAO employeeDAO, Mapper mapper) {
        this.visitDAO=visitDAO;
        this.visitorDAO = visitorDAO;
        this.cardDAO = cardDAO;
        this.employeeDAO = employeeDAO;
        this.mapper = mapper;
    }

    @Override
    public VisitDTO saveVisit(VisitDTO visitDTO) {
        Optional<Card> tempCard = cardDAO.findById(visitDTO.getCard().getCardId());
        tempCard.get().setCardNo(visitDTO.getCard().getCardNo());
        tempCard.get().setStatus("Not Available");
        return mapper.toVisitDTO(visitDAO.save(mapper.toVisitEntity(visitDTO)));
    }

    @Override
    public VisitDTO updateVisit(VisitDTO visitDTO) {
        Optional<Visit> tempVisit = visitDAO.findById(visitDTO.getVisitId());
        tempVisit.get().setVisitor(mapper.toVisitorEntity(visitDTO.getVisitor()));
        tempVisit.get().setCard(mapper.toCardEntity(visitDTO.getCard()));
        tempVisit.get().setVisitDate(visitDTO.getVisitDate());
        tempVisit.get().setCheckIn(visitDTO.getCheckIn());
        tempVisit.get().setCheckOut(visitDTO.getCheckOut());
        tempVisit.get().setPurpose(visitDTO.getPurpose());

        Optional<Card> tempCard = cardDAO.findById(visitDTO.getCard().getCardId());
        tempCard.get().setCardNo(visitDTO.getCard().getCardNo());
        tempCard.get().setStatus("Available");

        return mapper.toVisitDTO(visitDAO.getReferenceById(visitDTO.getVisitId()));
    }

    @Override
    public void deleteVisit(Integer id) {
        visitDAO.deleteById(id);
    }

    @Override
    public VisitDTO findVisitById(Integer id) {
        return mapper.toVisitDTO(visitDAO.getReferenceById(id));
    }

    @Override
    public List<VisitDTO> findAllVisits() {
        return mapper.toVisitDTOList(visitDAO.findAll());
    }

    @Override
    public List<VisitDTO> findVisitByVisitorNic(String nic) {
        return mapper.toVisitDTOList(visitDAO.findVisitsByVisitorNic(nic));
    }

    @Override
    public List<VisitDTO> findOverdueVisits() {
        return mapper.toVisitDTOList(visitDAO.findOverdueVisits());
    }

    @Override
    public VisitorDTO findVisitor(Integer id) {
        return mapper.toVisitorDTO(visitorDAO.getReferenceById(id));
    }

    @Override
    public CardDTO findAvailableCard() {
        return mapper.toCardDTO(cardDAO.findAvailableCard());
    }

    @Override
    public EmployeeDTO findEmployee(Integer id) {
        return mapper.toEmployeeDTO(employeeDAO.getReferenceById(id));
    }

    @Override
    public boolean existsVisit(Integer id) {
        return visitDAO.existsByVisitId(id);
    }

    @Override
    public boolean existsVisitor(Integer id) {
        return visitorDAO.existsByVisitorId(id);
    }

    @Override
    public boolean existsEmployee(Integer id) {
        return employeeDAO.existsById(id);
    }

}
