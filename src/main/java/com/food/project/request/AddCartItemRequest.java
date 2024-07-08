package com.food.project.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddCartItemRequest {
	
	private Long foodId;
	private int quantity;
	private List<String> ingredients;

}
