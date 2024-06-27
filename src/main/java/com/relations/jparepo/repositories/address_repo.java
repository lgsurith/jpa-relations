package com.relations.jparepo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.relations.jparepo.models.Address;

public interface address_repo  extends JpaRepository<Address, Integer>{
    
}
