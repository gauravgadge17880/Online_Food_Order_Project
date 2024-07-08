package com.food.project.service;

import java.util.List;

import com.food.project.model.Category;

public interface CategoryService {
	public Category createCategory(String name, Long userId) throws Exception;
	
	public List<Category> findCategoryByRestaurant(Long id) throws Exception;
	
	public Category findCategoryById(Long id) throws Exception;
	
	
	
	

}
