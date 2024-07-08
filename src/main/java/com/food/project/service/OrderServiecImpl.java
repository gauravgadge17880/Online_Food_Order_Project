package com.food.project.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.food.project.model.Address;
import com.food.project.model.Order;
import com.food.project.model.Restaurant;
import com.food.project.model.User;
import com.food.project.repository.AddressRepostitory;
import com.food.project.repository.OrderItemRepository;
import com.food.project.repository.OrderRepository;
import com.food.project.repository.UserRepository;
import com.food.project.request.OrderRequest;

public class OrderServiecImpl implements OrderService{
	
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private AddressRepostitory addressRepostitory;
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@Override
	public Order createOrder(OrderRequest order, User user) throws Exception {
		Address shippAddress=order.getDeliveryAddress();
		Address saveAddress = addressRepostitory.save(shippAddress);
		
		if(!user.getAddress().contains(saveAddress)) {
			user.getAddress().add(saveAddress);
			userRepository.save(user);
		}
		
		Restaurant restaurant = restaurantService.findRestaurantById(order.getRestaurantId());
		
		Order createOrder = new Order();
		
		createOrder.setCustomer(user);
		createOrder.setCreatedAt(new Date(1));
		createOrder.setOrderStatus("pending...");
		createOrder.setDeliveryAddress(saveAddress);
		createOrder.setRestaurant(restaurant);
		
		return null;
	}

	@Override
	public Order updateOrder(Long orderId, String orderStatus) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cancelOrder(Long orderId) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Order> getUsersOrder(Long userId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getRestaurantsOrder(Long restaurantId, String orderStatus) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
