package com.food.project.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientCategoryRequest {
	
	private String name;
	private Long restaurant;
	

}
