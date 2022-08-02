package com.victorglcosta.coursemc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.victorglcosta.coursemc.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

}
