package com.food.project.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.project.model.Address;
import com.food.project.model.Restaurant;
import com.food.project.model.RestaurantDto;
import com.food.project.model.User;
import com.food.project.repository.AddressRepostitory;
import com.food.project.repository.RestaurantRepository;
import com.food.project.repository.UserRepository;
import com.food.project.request.CreateRestaurantRequest;

@Service
public class RestaurantServiceImpl implements RestaurantService {
	@Autowired
	private RestaurantRepository restaurantRepository;
	@Autowired
	private AddressRepostitory addressRepostitory;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Restaurant createRestaurant(CreateRestaurantRequest req, User user) {
		Address address = addressRepostitory.save(req.getAddress());
		
		Restaurant restaurant = new Restaurant();
		restaurant.setAddress(req.getAddress());
		restaurant.setContactInformation(req.getContactInformation());
		restaurant.setCuisineType(req.getCuisineType());
		restaurant.setDescription(req.getDescription());
		restaurant.setImages(req.getImages());
		restaurant.setName(req.getName());
		restaurant.setOpeningHours(req.getOpeningHours());
		restaurant.setRegistrationDate(LocalDateTime.now());
		restaurant.setOwner(user);
		return restaurantRepository.save(restaurant);
	}

	@Override
	public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updateRestaurant) throws Exception {
		Restaurant restaurant = findRestaurantById(restaurantId);
		
		if(restaurant.getCuisineType()!=null) {
			restaurant.setCuisineType(updateRestaurant.getCuisineType());
		}
		if(restaurant.getDescription()!=null) {
			restaurant.setDescription(updateRestaurant.getDescription());
		}
		if(restaurant.getName()!=null) {
			restaurant.setName(updateRestaurant.getName());
		}
		return restaurantRepository.save(restaurant);
	}

	@Override
	public void deleteRestaurant(Long restaurantId) throws Exception {
		Restaurant restaurant = findRestaurantById(restaurantId);
		
		restaurantRepository.delete(restaurant);
		
	}

	@Override
	public List<Restaurant> getAllRestaurant() {
		Restaurant restaurant =(Restaurant) restaurantRepository.findAll();
		return (List<Restaurant>) restaurant;
	}

	@Override
	public List<Restaurant> searchRestaurant(String keyword) {
		// TODO Auto-generated method stub
		return restaurantRepository.findBySearchQuery(keyword);
	}

	@Override
	public Restaurant findRestaurantById(Long id) throws Exception {
	Optional<Restaurant> opt = restaurantRepository.findById(id);
	
	if(opt.isEmpty()) {
		throw new Exception("restaurant not found with id" +id);
	}
		return opt.get();
	}

	@Override
	public Restaurant getRestaurantByUserId(Long userId) throws Exception {
		Restaurant restaurant = findRestaurantById(userId);
		if(restaurant==null) {
			throw new Exception("restaurant not found with owner id "+userId);
		}
		
		return restaurant;
	}

	@Override
	public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception {
		Restaurant restaurant = findRestaurantById(restaurantId);
		RestaurantDto dto = new RestaurantDto();
		dto.setDescription(restaurant.getDescription());
		dto.setImages(restaurant.getImages());
		dto.setTitle(restaurant.getName());
		dto.setId(restaurantId);
		
		boolean isFavorited = false;
		List<RestaurantDto> favorites = user.getFavorites();
		for(RestaurantDto favorite : favorites) {
			
			if(favorite.getId().equals(restaurant)) {
				isFavorited = true;
				break;
			}
			if(isFavorited) {
				favorites.removeIf(fav -> fav.getId().equals(restaurantId));
			}else {
				favorites.add(dto);
			}
			
		}
		
		
		userRepository.save(user);
		return dto;
	}

	@Override
	public Restaurant updateRestaurantStatus(Long id) throws Exception {
		Restaurant restaurant = findRestaurantById(id);
		restaurant.setOpen(!restaurant.isOpen());
		return restaurantRepository.save(restaurant);
	}

	
	

}
