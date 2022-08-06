package com.victorglcosta.coursemc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victorglcosta.coursemc.domain.Customer;
import com.victorglcosta.coursemc.repositories.CustomerRepository;
import com.victorglcosta.coursemc.services.exceptions.ObjectNotFoundException;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository repo;
	
	public Customer find(Integer id) {
		Optional<Customer> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Type: " + Customer.class.getName()));
	}
}
