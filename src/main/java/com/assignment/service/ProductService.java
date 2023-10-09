package com.assignment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.assignment.dto.Response;
import com.assignment.entity.Product;
import com.assignment.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public ResponseEntity<?> createProduct(Product product) {
		try {
			productRepository.save(product);
			return new ResponseEntity<Response>(new Response("success", "Product created successfully"),
					HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Response>(new Response("error", e.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<?> getProducts() {
		try {
			List<Product> list = productRepository.findAll();
			return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Response>(new Response("error", e.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<?> deleteProducts(Integer productId) {
		try {
			productRepository.deleteById(productId);
			return new ResponseEntity<Response>(new Response("success", "Product deleted successfully"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Response>(new Response("error", e.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
