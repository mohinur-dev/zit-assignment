package com.assignment.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.assignment.dto.Response;
import com.assignment.entity.Cart;
import com.assignment.entity.Product;
import com.assignment.entity.User;
import com.assignment.repository.CartRepository;
import com.assignment.repository.ProductRepository;
import com.assignment.repository.UserRepository;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private UserRepository userRepository;

	public ResponseEntity<?> addToCart(Integer userId, Integer productId, Integer quantity) {
		try {
			User user = userRepository.findById(userId).get();
			Product product = productRepository.findById(productId).get();

			Cart cart = new Cart();
			cart.setUser(user);

			Set<Product> products = new HashSet<>();
			products.add(product);

			cart.setProduct(products);
			cart.setQuantity(quantity);

			cartRepository.save(cart);
			return new ResponseEntity<Response>(new Response("success", "Product added to cart"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Response>(new Response("error", e.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<?> updateCart(Integer cartId, Integer quantity) {
		try {
			
			Cart cart =cartRepository.findById(cartId).get();

			cart.setQuantity(quantity);

			cartRepository.save(cart);
			return new ResponseEntity<Response>(new Response("success", "Cart updated"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Response>(new Response("error", e.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<?> deleteFormCart(Integer cartId) {
		try {
			cartRepository.deleteById(cartId);
			return new ResponseEntity<Response>(new Response("success", "Product deleted form cart"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Response>(new Response("error", e.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<?> getCartItems(Integer userId) {
		try {
			User user = userRepository.findById(userId).get();

			List<Cart> list = cartRepository.findByUser(user);
			return new ResponseEntity<List<Cart>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Response>(new Response("error", e.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
