package com.bytes.train.dto;

public class TicketDto {
	
	private int customer_id;
	private int ticketId;
	private String subject;
	private String description;
	private String priority;
	private String status;
	private int agentID;
	private String agentName;

	public TicketDto() {

	}

	public TicketDto(int customer_id, int ticketId, String subject, String description, String priority, String status,
			int agentID, String agentName) {
		super();
		this.customer_id = customer_id;
		this.ticketId = ticketId;
		this.subject = subject;
		this.description = description;
		this.priority = priority;
		this.status = status;
		this.agentID = agentID;
		this.agentName = agentName;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
