package com.food.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.food.project.model.IngredientItem;

@Repository
public interface IngredientItemRepository extends JpaRepository<IngredientItem, Long> {

	List<IngredientItem> findByRestaurantId(Long id);
}
