package com.ceyentra.visitor_management_system.dto;

import com.ceyentra.visitor_management_system.entity.Card;
import com.ceyentra.visitor_management_system.entity.Employee;
import com.ceyentra.visitor_management_system.entity.Visitor;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VisitDTO {

    private int visitId;
    private Visitor visitor;
    private Employee employee;
    private Card card;
    private LocalDate visitDate;
    private LocalTime checkIn;
    private LocalTime checkOut;
    private String purpose;

}
