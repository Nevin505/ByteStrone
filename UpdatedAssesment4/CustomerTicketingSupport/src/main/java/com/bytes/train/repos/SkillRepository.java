package com.bytes.train.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bytes.train.entities.Skill;

public interface SkillRepository extends JpaRepository<Skill, Integer> {
	
	Skill findBySkillName(String skills);
}
