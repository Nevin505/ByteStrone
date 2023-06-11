package com.bytes.train.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bytes.train.entities.Agent;
import com.bytes.train.entities.Category;

public interface AgentRepository extends JpaRepository<Agent,Integer> {
	
	 List<Agent> findByCategory(Category category);
	 

}
