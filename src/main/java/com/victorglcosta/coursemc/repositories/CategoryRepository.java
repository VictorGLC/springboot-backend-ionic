package com.victorglcosta.coursemc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.victorglcosta.coursemc.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
