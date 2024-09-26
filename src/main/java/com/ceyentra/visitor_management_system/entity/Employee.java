package com.ceyentra.visitor_management_system.entity;

import com.ceyentra.visitor_management_system.entity.enums.Floor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "employee")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Integer id;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "building_id", nullable = false)
    private Building building;

    @Column(name = "floor", nullable = false)
    private Floor floor;

}
