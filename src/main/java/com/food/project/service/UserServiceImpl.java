package com.food.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.method.AuthorizeReturnObject;
import org.springframework.stereotype.Service;

import com.food.project.config.JwtProvider;
import com.food.project.model.User;
import com.food.project.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	

	@Override
	public User findUserByJwtToken(String jwt) throws Exception {
		String email=jwtProvider.getEmailFromJwtToken(jwt);
		User user = findUserByEmail(email);
		return user;
	}

	@Override
	public User findUserByEmail(String email) throws Exception {
	
		User user = userRepository.findByEmail(email);
		if(user==null) {
			throw new Exception("user not found");
			
		}
		return user;
	}
	

}
