package com.bytes.train.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bytes.train.entities.Category;

public interface CategoryRespository extends JpaRepository<Category, Integer> {

////	int fi category_name
//	 @Query("SELECT c FROM Category c WHERE c.category_name = :categoryName")
//    Category findByCategoryName(@Param("categoryName") String categoryName);
//	categoryName
	
	Category findByCategoryName(String categoryName);

}
