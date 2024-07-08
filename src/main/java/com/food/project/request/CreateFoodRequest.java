package com.food.project.request;

import java.util.List;

import com.food.project.model.Category;
import com.food.project.model.IngredientItem;

import lombok.Data;

@Data
public class CreateFoodRequest {
	private String name;
	private String description;
	private Long price;
	
	private Category category;
	
	private List<String> images;
	
	private Long restaurantId;
	
	private boolean vegetarin;
	
	private boolean seasional;
	
	private List<IngredientItem> ingredients;
	

}
