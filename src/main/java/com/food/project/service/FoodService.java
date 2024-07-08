package com.food.project.service;

import java.util.List;

import com.food.project.model.Category;
import com.food.project.model.Food;
import com.food.project.model.Restaurant;
import com.food.project.request.CreateFoodRequest;

public interface FoodService {

	public Food CreateFood(CreateFoodRequest req, Category category, Restaurant restaurant);
		
	void deleteFood(Long foodId) throws Exception;
	
	public List<Food> getRestaurantsFood(Long restaurantId,
											boolean isVegitarain,
											boolean isNonveg,
											boolean isSeasonal,
											String foodCategory);
	
	public List<Food> searchFood(String  Keyword);
	public Food findFoodById(Long foodId)throws Exception;
	
	public Food updateAvailabilityStatus(Long foodId) throws Exception;
	
}
