package com.relations.jparepo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.relations.jparepo.models.Department;

public interface department_repo extends JpaRepository<Department, Integer>{ 
} 
