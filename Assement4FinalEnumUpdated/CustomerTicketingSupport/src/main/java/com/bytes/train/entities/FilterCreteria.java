package com.bytes.train.entities;

public class FilterCreteria {
	
	String status;

	
	public FilterCreteria() {
		
	}
	
	public FilterCreteria(String status) {
		super();
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

}
