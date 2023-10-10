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

	// add product
	public ResponseEntity<?> createProduct(Product product) {
		try {
			productRepository.save(product);
			return new ResponseEntity<Response>(new Response("success", "Product created successfully"),
					HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Response>(new Response("error", e.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// get all product
	public ResponseEntity<?> getProducts() {
		try {
			List<Product> productList = productRepository.findAll();
			if (productList.isEmpty()) {
				return new ResponseEntity<Response>(new Response("Not found", "Product list is empty"),
						HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
			}

		} catch (Exception e) {
			return new ResponseEntity<Response>(new Response("error", e.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// get product by product id
	public ResponseEntity<?> getProductById(Integer productId) {
		try {
			Product product = productRepository.findById(productId).get();
			return new ResponseEntity<Product>(product, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Response>(new Response("Not found", "Product not found"), HttpStatus.NOT_FOUND);
		}
	}

}
