package com.ceyentra.visitor_management_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "visitor")
@Entity
public class Visitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visitor_id")
    private int visitorId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone_no", nullable = false, unique = true)
    private String phoneNo;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "nic", unique = true)
    private String nic;

}
