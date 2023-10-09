package com.assignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.assignment.dto.AuthRequest;
import com.assignment.dto.Response;
import com.assignment.entity.User;
import com.assignment.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	// user registration
	public ResponseEntity<?> createUser(User user) {
		try {
			userRepository.save(user);
			return new ResponseEntity<Response>(new Response("success", "User registered successfully"),
					HttpStatus.CREATED);
		} catch (DataIntegrityViolationException e) {
			return new ResponseEntity<Response>(new Response("error", "Email already registered"), HttpStatus.CONFLICT);
		}
	}

	// user simple authentication
	public ResponseEntity<?> authenticate(AuthRequest authRequest) {
		try {
			User user = userRepository.findByEmailAndPassword(authRequest.getEmail(), authRequest.getPassword());
			if (user != null) {
				return new ResponseEntity<Response>(new Response("success", "User authenticated"), HttpStatus.OK);
			} else {
				return new ResponseEntity<Response>(new Response("error", "Email or Password does not match"),
						HttpStatus.FORBIDDEN);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<Response>(new Response("error", e.toString()), HttpStatus.CONFLICT);
		}
	}
}
