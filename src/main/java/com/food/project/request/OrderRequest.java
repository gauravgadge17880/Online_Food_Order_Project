package com.food.project.request;

import com.food.project.model.Address;

import lombok.Data;

@Data
public class OrderRequest {
	private Long restaurantId;
	private Address deliveryAddress;

}
