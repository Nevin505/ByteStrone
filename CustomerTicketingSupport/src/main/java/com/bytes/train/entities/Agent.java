package com.bytes.train.entities;



import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table

public class Agent {
	@Id
	private int agentID;
	private String agentName;
	@Embedded
	private Address address;
	
	public Agent() {
		
	}
	public Agent(int agentID, String agentName, Address address) {
		super();
		this.agentID = agentID;
		this.agentName = agentName;
		this.address = address;
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
	

}
