package com.bytes.train.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bytes.train.entities.Agent;
import com.bytes.train.entities.Category;
import com.bytes.train.entities.Skill;

public interface AgentRepository extends JpaRepository<Agent, Integer> {
	
	List<Agent> findByCategoryIn(List<Category> agentCategories);
	
	List<Agent> findBySkillsInAndActive(List<Skill> skills,String active);
	
	
}
