package com.food.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

@RestController
@RequestMapping("/home")
public class HomeController {
	
	@GetMapping("/get")
	public ResponseEntity<String> homeController(){
		return new ResponseEntity<>("welcome to food delivery project", HttpStatus.OK);
	}

}
