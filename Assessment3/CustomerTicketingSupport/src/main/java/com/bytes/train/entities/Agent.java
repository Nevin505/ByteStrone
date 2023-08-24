package com.bytes.train.entities;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.MapKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table

public class Agent extends UserDetails {

	@Embedded
	private Address address;

	private int agentCount;
	
	private String active;
	
    private int experiance;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)

	@JoinTable(name = "agent_category", joinColumns = @JoinColumn(name = "agent_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "categoryId"))

	private List<Category> category = new LinkedList<>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "agent_skills", joinColumns = @JoinColumn(name = "agent_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "skill_id", referencedColumnName = "skillId"))
	private List<Skill> skills = new LinkedList<>();
	
	@ElementCollection
    @CollectionTable(name = "agent_skill_expertise", joinColumns = @JoinColumn(name = "agent_id"))
    @MapKeyJoinColumn(name = "skill_id")
    @Column(name = "expertise_level")
    private Map<Skill, Integer> skillExpertiseLevels = new HashMap<>();

	public Agent() {
		super();
	}

	public Agent(Address address, int agentCount, String status, int experiance, List<Category> category,
			List<Skill> skills) {
		super();
		this.address = address;
		this.agentCount = agentCount;
		this.active = status;
		this.experiance = experiance;
		this.category = category;
		this.skills = skills;
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

	public int getAgentCount() {
		return agentCount;
	}

	public void setAgentCount(int agentCount) {
		this.agentCount = agentCount;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public String getStatus() {
		return active;
	}
	public void setStatus(String status) {
		this.active = status;
	}

	public int getExperiance() {
		return experiance;
	}
	public void setExperiance(int experiance) {
		this.experiance = experiance;
	}

	@Override
	public String toString() {
		return "Agent [address=" + address + ", agentCount=" + agentCount + ", active=" + active + ", experiance="
				+ experiance + ", category=" + category + ", skills=" + skills + ", skillExpertiseLevels="
				+ skillExpertiseLevels + ", getName()=" + getName() + "]";
	}
	
	
	
	

}
