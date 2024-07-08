package com.food.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.food.project.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{
	public Cart findByCustomerId(Long userId);
	
}
