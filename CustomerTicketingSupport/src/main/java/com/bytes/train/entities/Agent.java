package com.bytes.train.entities;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table

public class Agent {
	@Id
	private int agentID;
	private String agentName;

	private String agentPassword;

	@Embedded
	private Address address;

	@ManyToOne
	private Category category;

	public Agent() {

	}

	public Agent(int agentID, String agentName, String agentPassword, Address address, Category category) {
		super();
		this.agentID = agentID;
		this.agentName = agentName;
		this.agentPassword = agentPassword;
		this.address = address;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
