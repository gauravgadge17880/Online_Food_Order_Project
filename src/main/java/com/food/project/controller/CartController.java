package com.food.project.controller;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.food.project.model.Cart;
import com.food.project.model.CartItem;
import com.food.project.request.AddCartItemRequest;
import com.food.project.request.UpdateCartItemRequest;
import com.food.project.service.CartService;
import com.food.project.service.UserService;

@RestController
@RequestMapping("/api")
public class CartController {
	@Autowired
	private CartService cartService;
	
@Autowired
private UserService userService;
	
	@PutMapping("/cart/add")
	public ResponseEntity<CartItem> addItemToCart (@RequestBody AddCartItemRequest req,
				@RequestHeader("Authorization") String jwt)throws Exception{
		CartItem cartItem = cartService.addItemToCart(req, jwt);
		return new ResponseEntity<>(cartItem,HttpStatus.OK);
	}
	
	@PutMapping("/cart-item/update")
	public ResponseEntity<CartItem> updateCartItemQuantity(@RequestBody UpdateCartItemRequest req,
				@RequestHeader("Authorization") String jwt)throws Exception{
		CartItem cartItem = cartService.updateCartItemQuantity(req.getCartItemId(), req.getQuantity());
		return new ResponseEntity<>(cartItem,HttpStatus.OK);
	}
	

	@DeleteMapping("/cart-item/clear")
	public ResponseEntity<Cart> removeCartItem(
				@RequestHeader("Authorization") String jwt)throws Exception{
		User user=(User) userService.findUserByJwtToken(jwt);
		Cart cart = cartService.clearCart(((Cart) user).getId());
		return new ResponseEntity<>(cart,HttpStatus.OK);
	}
	

	@GetMapping("/cart")
	public ResponseEntity<CartItem> findUserCart(@RequestBody UpdateCartItemRequest req,
				@RequestHeader("Authorization") String jwt)throws Exception{
		
		User user=(User) userService.findUserByJwtToken(jwt);
		CartItem cartItem = cartService.updateCartItemQuantity(req.getCartItemId(), req.getQuantity());
		return new ResponseEntity<>(cartItem,HttpStatus.OK);
	}
	
	
	
	

}
