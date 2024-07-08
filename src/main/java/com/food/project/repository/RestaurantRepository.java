package com.food.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.food.project.model.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
	@Query("SELECT r FROM Restaurant r WHERE lower(r.name) LIKE lower(concat('%', :query, '%'))" +
			"OR lower(r.cuisineType) LIKE (Concat('%', : query, '%'))")
	List<Restaurant> findBySearchQuery(String query);

	public Restaurant findByOwnerId(Long userId);
	
}
