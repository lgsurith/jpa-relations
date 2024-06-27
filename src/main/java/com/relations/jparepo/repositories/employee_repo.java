package com.relations.jparepo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.relations.jparepo.models.Employee;

public interface employee_repo extends JpaRepository<Employee, Integer>{
    
}
