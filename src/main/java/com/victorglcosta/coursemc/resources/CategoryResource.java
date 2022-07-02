package com.victorglcosta.coursemc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victorglcosta.coursemc.domain.Category;

@RestController
@RequestMapping(value="/categories")
public class CategoryResource {
	
	@GetMapping
	public List<Category> toList() {
		Category cat1 = new Category(1, "Computing");
		Category cat2 = new Category(2, "Office");
		
		List<Category> list = new ArrayList<>();
		list.add(cat1);
		list.add(cat2);
		
		return list;
	}

}
