package com.food.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.food.project.model.IngredientCategory;
import com.food.project.model.IngredientItem;
import com.food.project.request.IngredientCategoryRequest;
import com.food.project.request.IngredientRequest;
import com.food.project.service.IngredientsService;

@RestController
@RequestMapping("/api/admin/ingredients")
public class IngredientController {
	@Autowired
	private IngredientsService ingredientsService;
	
	@PostMapping("/category")
	public ResponseEntity<IngredientCategory> createIngredientCategory(
			@RequestBody IngredientCategoryRequest req) throws Exception{
		
		IngredientCategory item = ingredientsService.createIngredientCategory(req.getName(), req.getRestaurant());
		return new ResponseEntity<>(item,HttpStatus.CREATED);
	}
	
	@PostMapping("/")
	public ResponseEntity<IngredientItem> createIngredientItem(
			@RequestBody IngredientRequest req) throws Exception{
		
		IngredientItem item = ingredientsService.createIngredientItem(req.getRestaurantId(),req.getName(), req.getCategoryId());
		return new ResponseEntity<>(item,HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}/stock")
	public ResponseEntity<IngredientItem> updateIngredientStock(
			@PathVariable Long id)throws Exception{
		IngredientItem item = ingredientsService.updateStock(id);	
		
		return new ResponseEntity<>(item,HttpStatus.OK);
		
	}
	@GetMapping("/restaurant/{id}")
	public ResponseEntity<List<IngredientItem>> getRestaurantIngredient(
			@PathVariable Long id)throws Exception{
		List<IngredientItem> item = ingredientsService.findRestaurantIngredients(id);
		return new ResponseEntity<>(item,HttpStatus.OK);
		
	}
	
	@GetMapping("/restaurant/{id}/category")
	public ResponseEntity<List<IngredientCategory>> getRestaurantIngredientCategory(
			@PathVariable Long id)throws Exception{
		List<IngredientCategory> item = ingredientsService.findIngredientCategoryByRestaurantId(id);
		return new ResponseEntity<>(item,HttpStatus.OK);
		
	}



}
















