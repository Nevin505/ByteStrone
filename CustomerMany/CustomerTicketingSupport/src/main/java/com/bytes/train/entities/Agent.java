package com.bytes.train.entities;

import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table

public class Agent {
	@Id
	private int agentID;
	private String agentName;

	private String email;

	private String agentPassword;

	@Embedded
	private Address address;

	private String role;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)

	@JoinTable(name = "agent_category", joinColumns = @JoinColumn(name = "agent_id", referencedColumnName = "agentID"), inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "categoryId"))
//    
	private List<Category> category = new LinkedList<>();;

	public Agent() {

	}

	public Agent(int agentID, String agentName, String email, String agentPassword, Address address, String role,
			List<Category> category) {
		super();
		this.agentID = agentID;
		this.agentName = agentName;
		this.email = email;
		this.agentPassword = agentPassword;
		this.address = address;
		this.role = role;
		this.category = category;
	}

	public String getAgentPassword() {
		return agentPassword;
	}

	public void setAgentPassword(String agentPassword) {
		this.agentPassword = agentPassword;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public int getAgentID() {
		return agentID;
	}

	public void setAgentID(int agentID) {
		this.agentID = agentID;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public List<Category> getCategory() {
		return category;
	}

	public void setCategory(List<Category> category) {
		this.category = category;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
