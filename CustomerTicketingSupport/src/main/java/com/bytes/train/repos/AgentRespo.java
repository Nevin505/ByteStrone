package com.bytes.train.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bytes.train.entities.Agent;
import com.bytes.train.entities.Category;

public interface AgentRespo extends JpaRepository<Agent,Integer> {
	
	 List<Agent> findByCategory(Category category);
	 
//	 List<Agent>  findAgentCate

}
