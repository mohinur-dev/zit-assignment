package com.assignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.entity.Cart;
import com.assignment.entity.User;

public interface CartRepository extends JpaRepository<Cart, Integer> {
	public List<Cart> findByUser(User user);
}
