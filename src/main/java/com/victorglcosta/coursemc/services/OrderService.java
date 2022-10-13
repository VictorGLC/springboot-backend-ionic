package com.victorglcosta.coursemc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victorglcosta.coursemc.domain.Order;
import com.victorglcosta.coursemc.repositories.OrderRepository;
import com.victorglcosta.coursemc.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repo;
	
	public Order find(Integer id) {
		Optional<Order> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Type: " + Order.class.getName()));
	}
}
