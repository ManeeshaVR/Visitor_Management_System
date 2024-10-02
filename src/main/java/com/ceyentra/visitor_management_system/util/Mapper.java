package com.ceyentra.visitor_management_system.util;

import com.ceyentra.visitor_management_system.dto.*;
import com.ceyentra.visitor_management_system.entity.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Mapper {

    private final ModelMapper mapper;

    //Visitor Mapping
    public VisitorDTO toVisitorDTO(Visitor visitor) {
        return  mapper.map(visitor, VisitorDTO.class);
    }
    public Visitor toVisitorEntity(VisitorDTO visitorDTO) {
        return  mapper.map(visitorDTO, Visitor.class);
    }
    public List<VisitorDTO> toVisitorDTOList(List<Visitor> visitors) {
        return mapper.map(visitors, List.class);
    }

    //Visit Mapping
    public VisitDTO toVisitDTO(Visit visit) {
        return  mapper.map(visit, VisitDTO.class);
    }
    public Visit toVisitEntity(VisitDTO visitDTO) {
        return  mapper.map(visitDTO, Visit.class);
    }
    public List<VisitDTO> toVisitDTOList(List<Visit> visits) {
        return mapper.map(visits, List.class);
    }

    //Employee Mapping
    public EmployeeDTO toEmployeeDTO(Employee employee) {
        return  mapper.map(employee, EmployeeDTO.class);
    }
    public Employee toEmployeeEntity(EmployeeDTO employeeDTO) {
        return  mapper.map(employeeDTO, Employee.class);
    }
    public List<EmployeeDTO> toEmployeeDTOList(List<Employee> employees) {
        return mapper.map(employees, List.class);
    }

    //Building Mapping
    public BuildingDTO toBuildingDTO(Building building) {
        return  mapper.map(building, BuildingDTO.class);
    }
    public Building toBuildingEntity(BuildingDTO buildingDTO) {
        return  mapper.map(buildingDTO, Building.class);
    }
    public List<BuildingDTO> toBuildingDTOList(List<Building> buildings) {
        return mapper.map(buildings, List.class);
    }

    //Card Mapping
    public CardDTO toCardDTO(Card card) {
        return  mapper.map(card, CardDTO.class);
    }
    public Card toCardEntity(CardDTO cardDTO) {
        return  mapper.map(cardDTO, Card.class);
    }
    public List<CardDTO> toCardDTOList(List<Card> cards) {
        return mapper.map(cards, List.class);
    }

}
