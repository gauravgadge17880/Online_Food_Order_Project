package com.food.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.food.project.model.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

	List<Food> findByRestaurantId(Long restaurantId);
	@Query("SELECT f FROM Food f WHERE f.name LIKE %:keyword% OR f.foodCategory.name LIKE %:keyoword%")
	List<Food> searchFood(@Param("keyword") String keyword);
}
