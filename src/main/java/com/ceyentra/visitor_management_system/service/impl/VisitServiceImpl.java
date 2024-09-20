package com.ceyentra.visitor_management_system.service.impl;

import com.ceyentra.visitor_management_system.dao.CardDAO;
import com.ceyentra.visitor_management_system.dao.VisitDAO;
import com.ceyentra.visitor_management_system.dao.VisitorDAO;
import com.ceyentra.visitor_management_system.entity.Card;
import com.ceyentra.visitor_management_system.entity.Visit;
import com.ceyentra.visitor_management_system.entity.Visitor;
import com.ceyentra.visitor_management_system.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
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

    @Autowired
    public VisitServiceImpl(VisitDAO visitDAO, VisitorDAO visitorDAO, CardDAO cardDAO) {
        this.visitDAO=visitDAO;
        this.visitorDAO = visitorDAO;
        this.cardDAO = cardDAO;
    }

    @Override
    public Visit saveVisit(Visit visit) {
        Optional<Card> tempCard = cardDAO.findById(visit.getCard().getCardId());
        tempCard.get().setCardNo(visit.getCard().getCardNo());
        tempCard.get().setStatus("Not Available");
        return visitDAO.save(visit);
    }

    @Override
    public Visit updateVisit(Visit visit) {
        Optional<Visit> tempVisit = visitDAO.findById(visit.getVisitId());
        tempVisit.get().setVisitor(visit.getVisitor());
        tempVisit.get().setCard(visit.getCard());
        tempVisit.get().setVisitDate(visit.getVisitDate());
        tempVisit.get().setCheckIn(visit.getCheckIn());
        tempVisit.get().setCheckOut(visit.getCheckOut());
        tempVisit.get().setPurpose(visit.getPurpose());

        Optional<Card> tempCard = cardDAO.findById(visit.getCard().getCardId());
        tempCard.get().setCardNo(visit.getCard().getCardNo());
        tempCard.get().setStatus("Available");

        return visitDAO.getReferenceById(visit.getVisitId());
    }

    @Override
    public void deleteVisit(Integer id) {
        visitDAO.deleteById(id);
    }

    @Override
    public Visit findVisitById(Integer id) {
        return visitDAO.getReferenceById(id);
    }

    @Override
    public List<Visit> findAllVisits() {
        return visitDAO.findAll();
    }

    @Override
    public List<Visit> findVisitByVisitorNic(String nic) {
        return visitDAO.findVisitsByVisitorNic(nic);
    }

    @Override
    public Visitor findVisitor(Integer id) {
        return visitorDAO.getReferenceById(id);
    }

    @Override
    public Card findAvailableCard() {
        return cardDAO.findAvailableCard();
    }

}
