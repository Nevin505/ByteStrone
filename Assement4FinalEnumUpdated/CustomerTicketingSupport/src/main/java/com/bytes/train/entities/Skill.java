package com.bytes.train.entities;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
public class Skill {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int skillId;
	
	String skillName;
	
	@ManyToOne
	@JoinColumn(name = "categoryId")
	Category category;
	
	

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "skills")
	@JsonBackReference
	List<Agent> agentIds=new LinkedList<>();
	
	public Skill() {
		
	}
	 
	public Skill(int skillId, String skillName, Category category) {
		super();
		this.skillId = skillId;
		this.skillName = skillName;
		this.category = category;
	}
	public int getSkillId() {
		return skillId;
	}
	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public List<Agent> getAgentIds() {
		return agentIds;
	}
	public void setAgentIds(List<Agent> agentIds) {
		this.agentIds = agentIds;
	}
	
}
