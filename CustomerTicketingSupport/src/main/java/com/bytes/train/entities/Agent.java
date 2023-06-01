package com.bytes.train.entities;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table

public class Agent {
	@Id
	private int agentID;
	private String agentName;
	private long phoneNumber;
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
	public long getContactInformation() {
		return phoneNumber;
	}
	public void setContactInformation(long contactInformation) {
		this.phoneNumber = contactInformation;
	}
	
	

}
