package com.bytes.train.entities;

import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table

public class Agent extends UserDetails {
	
	@Embedded
	private Address address;


	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)

	@JoinTable(name = "agent_category", joinColumns = @JoinColumn(name = "agent_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "categoryId"))
   
	private List<Category> category = new LinkedList<>(); 
	
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "agent_skills", joinColumns = @JoinColumn(name = "agent_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "skill_id", referencedColumnName = "skillId"))
	private List<Skill> skills = new LinkedList<>();
	
	public Agent() {
	super();
	}
	
	public Agent(int id, String name, String userName, String email, String role, String userPassword, Address address,
			List<Category> category) {
		super(id, name, userName, email, role, userPassword);
		this.address = address;
		this.category = category;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Category> getCategory() {
		return category;
	}

	public void setCategory(List<Category> category) {
		this.category = category;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}
	
	
}

