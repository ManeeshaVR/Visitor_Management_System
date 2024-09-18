package com.ceyentra.visitor_management_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "card")
@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private int cardId;

    @Column(name = "card_no", nullable = false, unique = true)
    private Integer cardNo;

    @Column(name = "status", nullable = false, unique = true)
    private String status;

}
