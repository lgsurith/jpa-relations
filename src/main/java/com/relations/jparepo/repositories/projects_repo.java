package com.relations.jparepo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.relations.jparepo.models.Projects;

public interface projects_repo extends JpaRepository<Projects, Integer>{
    
}
