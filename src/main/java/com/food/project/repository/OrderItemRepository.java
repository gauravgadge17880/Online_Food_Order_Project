package com.food.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.food.project.model.Orderitem;

public interface OrderItemRepository extends JpaRepository<Orderitem, Long> {

}
