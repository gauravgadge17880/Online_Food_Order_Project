package com.food.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.food.project.model.CartItem;
@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
