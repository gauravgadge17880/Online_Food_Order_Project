package com.food.project.service;

import java.util.List;

import com.food.project.model.IngredientCategory;
import com.food.project.model.IngredientItem;

public interface IngredientsService {
	
	public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception;
	
	public IngredientCategory findIngredientCategoryById(Long id)throws Exception;
	
	public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id)throws Exception;
	
	public IngredientItem createIngredientItem(Long restaurantId, String ingredientName,Long categoryId)throws Exception;
	
	public List<IngredientItem> findRestaurantIngredients(Long restaurantId);
	
	public IngredientItem updateStock(Long id) throws Exception;
}
