package com.food.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.food.project.model.Food;
import com.food.project.model.Restaurant;
import com.food.project.model.User;
import com.food.project.request.CreateFoodRequest;
import com.food.project.responce.MessageResponse;
import com.food.project.service.FoodService;
import com.food.project.service.RestaurantService;
import com.food.project.service.UserService;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {

	@Autowired
	private FoodService foodService;

	@Autowired
	private UserService userService;

	@Autowired
	private RestaurantService restaurantService;

	@PostMapping()
	public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest req,
			@RequestHeader("Authorization") String jwt) throws Exception {
		User user = userService.findUserByJwtToken(jwt);
		Restaurant restaurant = restaurantService.findRestaurantById(req.getRestaurantId());
		Food food = foodService.CreateFood(req, req.getCategory(), restaurant);

		return new ResponseEntity<>(food, HttpStatus.CREATED);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long id, @RequestHeader("Authorization") String jwt)
			throws Exception {
		User user = userService.findUserByJwtToken(jwt);

		foodService.deleteFood(id);
		MessageResponse msg = new MessageResponse();
		msg.setMessage("food deleted successfuly");
		return new ResponseEntity<>(msg, HttpStatus.CREATED);

	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Food> updateFoodAvaibilityStatus(@PathVariable Long id,
			@RequestHeader("Authorization") String jwt) throws Exception {
		User user = userService.findUserByJwtToken(jwt);
		
		Food food = foodService.updateAvailabilityStatus(id);

		return new ResponseEntity<>(food, HttpStatus.CREATED);

	}
	

}
