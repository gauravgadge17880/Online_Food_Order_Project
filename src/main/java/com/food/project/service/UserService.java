package com.food.project.service;

import org.springframework.stereotype.Service;

import com.food.project.model.User;


public interface UserService {
	
	public User findUserByJwtToken(String Jwt)throws Exception;
	public User findUserByEmail(String email)throws Exception;

}
