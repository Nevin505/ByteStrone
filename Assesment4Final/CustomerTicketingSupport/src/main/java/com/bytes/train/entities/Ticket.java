package com.bytes.train.entities;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table

public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int ticketId;
	private String subject;
	@Column(length = 2000)
	private String description;
	private String priority;
	private String status = "Open";
	
	 private float satisfactionRating;

	@CreationTimestamp
	@Column(columnDefinition = "timestamp without time zone", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date creation_Date;

//	@UpdateTimestamp
	@Column(columnDefinition = "timestamp without time zone", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date assigined_Date = null;
	
//	private Date 

	@Column(columnDefinition = "timestamp without time zone", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date closedDate = null;

	@ManyToOne
	@JoinColumn(name = "customerid")
	Customer customer;

	@ManyToOne
	@JoinColumn(name = "agentID")
	Agent agentId;
	

	@ManyToOne
	@JoinColumn(name = "categoryId")
	Category categoryId;
	
   

	String categoryName;

	public Ticket() {

	}

	

	public Ticket(int ticketId, String subject, String description, String priority, String status, Date creation_Date,
			Date updated_Date, Date closedDate, Customer customer, Agent agentId, Category categoryId,
			Float satisfactionRating, String categoryName) {
		super();
		this.ticketId = ticketId;
		this.subject = subject;
		this.description = description;
		this.priority = priority;
		this.status = status;
		this.creation_Date = creation_Date;
		this.assigined_Date = updated_Date;
		this.closedDate = closedDate;
		this.customer = customer;
		this.agentId = agentId;
		this.categoryId = categoryId;
		this.satisfactionRating = satisfactionRating;
		this.categoryName = categoryName;
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

	public Date getCreation_Date() {
		return creation_Date;
	}

	public void setCreation_Date(Date creation_Date) {
		this.creation_Date = creation_Date;
	}

	public Date getUpdated_Date() {
		return assigined_Date;
	}

	public void setUpdated_Date(Date updated_Date) {
		this.assigined_Date = updated_Date;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Agent getAgentId() {
		return agentId;
	}

	public void setAgentId(Agent agentId) {
		this.agentId = agentId;
	}

	public Category getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Category categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Date getClosedDate() {
		return closedDate;
	}

	public void setClosedDate(Date closedDate) {
		this.closedDate = closedDate;
	}

	@PrePersist
	protected void onCreate() {
		this.creation_Date = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		if (this.closedDate == null) {
			this.assigined_Date = new Date();
			this.assigined_Date = new Date();
		}
	}

	public void closeTicket() {
		this.status = "Closed";
		this.closedDate = new Date();
	}
	

	public float getSatisfactionRating() {
		return satisfactionRating;
	}

	public void setSatisfactionRating(float satisfactionRating) {
		this.satisfactionRating = satisfactionRating;
	}

	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", subject=" + subject + ", description=" + description + ", priority="
				+ priority + ", status=" + status + ", creation_Date=" + creation_Date + ", updated_Date="
				+ assigined_Date + ", closedDate=" + closedDate + ", customer=" + customer + ", agentId=" + agentId
				+ ", categoryId=" + categoryId + ", satisfactionRating=" + satisfactionRating + ", categoryName="
				+ categoryName + "]";
	}

	
}
