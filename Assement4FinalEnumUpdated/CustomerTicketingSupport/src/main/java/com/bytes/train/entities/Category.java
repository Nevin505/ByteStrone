package com.bytes.train.entities;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
public class Category {
	@Id
	private Integer categoryId;
	private String categoryName;
	
	

	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "category")
	@JsonBackReference
	List<Agent> agentId=new LinkedList<>();
	
	
	public Category() {
		 
	}

	public Category(int categoryId, String categoryName, List<Agent> agentId) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.agentId = agentId;
	}

//	public String getCategoryName() {
//		return categoryName;
//	}
//
//	public void setCategoryName(String categoryName) {
//		this.categoryName = categoryName;
//	}

	public List<Agent> getAgentId() {
		return agentId;
	}

	public void setAgentId(List<Agent> agentId) {
		this.agentId = agentId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategory_name() {
		return categoryName;
	}

	public void setCategory_name(String categoryName) {
		this.categoryName = categoryName;
	}

}
