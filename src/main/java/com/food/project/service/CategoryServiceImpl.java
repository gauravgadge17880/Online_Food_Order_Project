package com.food.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.project.model.Category;
import com.food.project.model.Restaurant;
import com.food.project.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private RestaurantService restaurantService;
	
	

	@Override
	public Category createCategory(String name, Long userId) throws Exception {
		Restaurant restaurant = restaurantService.getRestaurantByUserId(userId);
		Category category = new Category();
		category.setName(name);
		category.setRestaurant(restaurant);
		
		
		return categoryRepository.save(category);
	}

	@Override
	public List<Category> findCategoryByRestaurant(Long id) throws Exception {
		Restaurant restaurant = restaurantService.getRestaurantByUserId(id);
		
		
		return categoryRepository.findByRestaurantId(restaurant.getId());
	}

	@Override
	public Category findCategoryById(Long id) throws Exception {
		// TODO Auto-generated method stub
		Optional<Category> optional=categoryRepository.findById(id);
		
		if(optional.isEmpty()) {
			throw new Exception("category not found");
			
		}
		return optional.get();
	}

}
