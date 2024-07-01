package com.relations.jparepo.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


import com.fasterxml.jackson.annotation.JsonManagedReference;

//note : javax is depricated so we use jakarta.
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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

    //setting up the jpa relationships
    //sets up a foreign key.
    //NOTE : JoinColumn specifies that its the owner of the relationship.
    
    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id" , nullable = false)
    private Address address;

    @ManyToOne
    @JoinColumn(name = "department_id" , nullable = false)
    private Department department;

    //to add multiple Cascade for ORM operations follow this.
    //CascadeType.PERSIST : when we save the entity, related entities are also saved.
    //CascadeType.MERGE : when we update the entity, related entities are also updated.
    @ManyToMany(cascade = {CascadeType.PERSIST , CascadeType.MERGE })
    @JoinTable(
        name = "Employee_Projects",
        joinColumns = @JoinColumn(name = "employee_id"),
        inverseJoinColumns = @JoinColumn(name = "projects_id" , nullable = false)
    )
    private Set<Projects> projects = new HashSet<>();

    //to add project functionality for many to many.
    public void addProject(Projects project){
        this.projects.add(project);
        project.getEmployees().add(this);
    }

    //setting up the getters and setters.
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdentifier() {
        return Identifier;
    }

    public void setIdentifier(String identifier) {
        Identifier = identifier;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<Projects> getProjects() {
        return projects;
    }

    public void setProjects(Set<Projects> projects) {
        this.projects = projects;
    }
}