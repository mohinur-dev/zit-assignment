package com.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.entity.Product;
import com.assignment.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/add")
	public ResponseEntity<?> createProduct(@Valid @RequestBody Product product) {
		return productService.createProduct(product);
	}

	@GetMapping("/get")
	public ResponseEntity<?> getProducts() {
		return productService.getProducts();
	}
	
}
