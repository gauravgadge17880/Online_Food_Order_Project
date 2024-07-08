package com.food.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.project.model.IngredientCategory;
import com.food.project.model.IngredientItem;
import com.food.project.model.Restaurant;
import com.food.project.repository.IngredientCategoryRepository;
import com.food.project.repository.IngredientItemRepository;

@Service
public class IngredientServiceImpl implements IngredientsService {

	@Autowired
	private IngredientItemRepository ingredientItemRepository;

	@Autowired
	private IngredientCategoryRepository ingredientCategoryRepository;

	@Autowired
	private RestaurantService restaurantService;

	@Override
	public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception {

		Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
		IngredientCategory category = new IngredientCategory();
		category.setRestaurant(restaurant);
		category.setName(name);

		return ingredientCategoryRepository.save(category);
	}

	@Override
	public IngredientCategory findIngredientCategoryById(Long id) throws Exception {
		Optional<IngredientCategory> opt = ingredientCategoryRepository.findById(id);
		if (opt.isEmpty()) {
			throw new Exception("Ingredient Category not found");
		}

		return opt.get();
	}

	@Override
	public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception {
		restaurantService.findRestaurantById(id);
		return ingredientCategoryRepository.findByRestaurantId(id);
	}

	@Override
	public IngredientItem createIngredientItem(Long restaurantId, String ingredientName, Long categoryId)
		throws Exception {
		Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
		
		IngredientCategory category = findIngredientCategoryById(categoryId);
		
		IngredientItem item = new IngredientItem();
		item.setName(ingredientName);
		item.setRestaurant(restaurant);
		item.setCategory(category);
		
		IngredientItem ingredient = ingredientItemRepository.save(item);
		category.getIngredients().add(ingredient);
		return ingredient;
	}

	
	@Override
	public List<IngredientItem> findRestaurantIngredients(Long restaurantId) {
		// TODO Auto-generated method stub
		return ingredientItemRepository.findByRestaurantId(restaurantId);
	}

	@Override
	public IngredientItem updateStock(Long id) throws Exception {
	Optional<IngredientItem> optionalIngredientItem = ingredientItemRepository.findById(id);
		if(optionalIngredientItem.isEmpty()) {
			throw new Exception("Ingredient not found..");
		}
	IngredientItem ingredientItem = optionalIngredientItem.get();
	ingredientItem.setStoke(!ingredientItem.isStoke());
			
	return ingredientItemRepository.save(ingredientItem);
	}

}
