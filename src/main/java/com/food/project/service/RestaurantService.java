package com.food.project.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.food.project.model.Restaurant;
import com.food.project.model.RestaurantDto;
import com.food.project.model.User;
import com.food.project.request.CreateRestaurantRequest;

@Repository
public interface RestaurantService {
	
	public Restaurant createRestaurant(CreateRestaurantRequest req, User user);
	
	public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updateRestaurant) throws Exception;
	
	public void deleteRestaurant(Long restaurantId) throws Exception;

	public List<Restaurant> getAllRestaurant();
	
	
	public Restaurant findRestaurantById(Long id) throws Exception;
	public Restaurant getRestaurantByUserId(Long id) throws Exception;
	public RestaurantDto addToFavorites(Long restaurantID, User user) throws Exception;
	public Restaurant updateRestaurantStatus(Long id) throws Exception;

	List<Restaurant> searchRestaurant(String keyword);
	
	
	
	
}
