package com.bytes.train.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bytes.train.entities.Category;

public interface CategoryRespository extends JpaRepository<Category, Integer> {


	
	Category findByCategoryName(String categoryName);

}
