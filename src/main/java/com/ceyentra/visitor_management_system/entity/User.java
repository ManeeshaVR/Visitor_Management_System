package com.ceyentra.visitor_management_system.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private String userId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "user_name", unique = true, nullable = false)
    private String userName;

    @Column(name = "password", unique = true, nullable = false)
    private String password;

}

