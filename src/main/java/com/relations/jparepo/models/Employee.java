package com.relations.jparepo.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
//note : javax is depricated so we use jakarta.
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {

    //indicates this is the primary key.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false , unique = true)
    private String Identifier;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false , unique = true)
    private String email;

    @Column(nullable = false)
    private LocalDate birthDate;

    // @Enumerated(EnumType.STRING)
    // @Column(nullable = false)
    // private EmployeeRole role;

    //setting up the jpa relationships
    //sets up a foreign key.
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}