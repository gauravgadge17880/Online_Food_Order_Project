package com.food.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.food.project.model.Address;

@Repository
public interface AddressRepostitory extends JpaRepository<Address, Long>{

}
