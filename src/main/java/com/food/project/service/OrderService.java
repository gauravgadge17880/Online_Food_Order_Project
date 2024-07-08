package com.food.project.service;

import java.util.List;

import com.food.project.model.Order;
import com.food.project.model.User;
import com.food.project.request.OrderRequest;

public interface OrderService { 
	public Order createOrder(OrderRequest order, User user) throws Exception;
		
	public Order updateOrder(Long orderId, String orderStatus) throws Exception;
	
	public void cancelOrder(Long orderId)throws Exception;
	public List<Order> getUsersOrder(Long userId) throws Exception;
	
	public List<Order> getRestaurantsOrder(Long restaurantId, String orderStatus) throws Exception;
	

}
