package com.food.project.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.food.project.model.Category;
import com.food.project.model.Food;
import com.food.project.model.Restaurant;
import com.food.project.repository.FoodRepository;
import com.food.project.request.CreateFoodRequest;

@Service
public class FoodServiceImpl implements FoodService {

	@Autowired
	private FoodRepository foodRepository;

	@Override
	public Food CreateFood(CreateFoodRequest req, Category category, Restaurant restaurant) {
		Food food = new Food();

		food.setFoodCategory(category);
		food.setRestaurant(restaurant);
		food.setDescription(req.getDescription());
		food.setImages(req.getImages());
		food.setName(req.getName());
		food.setPrice(req.getPrice());
		food.setIngreadients(req.getIngredients());

		food.setSeasonal(req.isSeasional());
		food.setVegetarian(req.isVegetarin());
		Food saveFood = foodRepository.save(food);

		restaurant.getFoods().add(saveFood);
		return saveFood;

	}

	@Override
	public void deleteFood(Long foodId) throws Exception {
		Food food = findFoodById(foodId);
		food.setRestaurant(null);
		foodRepository.save(food);

	}

	@Override
	public List<Food> getRestaurantsFood(Long restaurantId, boolean isVegitarain, boolean isNonveg, boolean isSeasonal,
			String foodCategory) {
		List<Food> foods = foodRepository.findByRestaurantId(restaurantId);
		if (isVegitarain) {
			foods = filterByVegetarian(foods, isVegitarain);
		}
		if (isNonveg) {
			foods = filterByNonveg(foods, isNonveg);
		}

		if (isSeasonal) {
			foods = filterBySeasonal(foods, isSeasonal);
		}

		if (foodCategory != null && !foodCategory.equals("")) {
			foods = filterByCategory(foods, foodCategory);
		}
		return null;
	}

	private List<Food> filterByCategory(List<Food> foods, String foodCategory) {
		// TODO Auto-generated method stub
		return foods.stream().filter(food -> {
			if (food.getFoodCategory() != null) {
				return food.getFoodCategory().getName().equals(foodCategory);
			}
			return false;

		}).collect(Collectors.toList());
	}

	private List<Food> filterBySeasonal(List<Food> foods, boolean isSeasonal) {
		// TODO Auto-generated method stub
		return foods.stream().filter(food -> food.isSeasonal() == isSeasonal).collect(Collectors.toList());
	}

	private List<Food> filterByNonveg(List<Food> foods, boolean isNonveg) {
		// TODO Auto-generated method stub
		return foods.stream().filter(food -> food.isVegetarian() == false).collect(Collectors.toList());
	}

	private List<Food> filterByVegetarian(List<Food> foods, boolean isVegitarain) {
		// TODO Auto-generated method stub
		return foods.stream().filter(food -> food.isVegetarian() == isVegitarain).collect(Collectors.toList());
	}

	@Override
	public List<Food> searchFood(String Keyword) {
		List<Food> foods = foodRepository.searchFood(Keyword);
		return foods  ;
	}

	@Override
	public Food findFoodById(Long foodId) throws Exception {
		Optional<Food> optional =  foodRepository.findById(foodId);
		if(optional.isEmpty()) {
			throw new Exception("food not exist..");
		}
		return optional.get();
	}

	@Override
	public Food updateAvailabilityStatus(Long foodId) throws Exception {
		 Food food = findFoodById(foodId);
		 food.setAvailable(!food.isAvailable());
		 return foodRepository.save(food);
		
	}

}
