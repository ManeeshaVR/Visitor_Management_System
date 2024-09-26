package com.ceyentra.visitor_management_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "employee")
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone_no", nullable = false, length = 12)
    private String phoneNo;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "position", nullable = false, length = 50)
    private String position;

    @Column(name = "status", nullable = false, length = 10)
    private String status;

}
