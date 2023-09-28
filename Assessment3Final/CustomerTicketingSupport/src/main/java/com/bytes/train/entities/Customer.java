package com.bytes.train.entities;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table
public class Customer  extends UserDetails{

	
	@Embedded
	private Address address;
	
	public Customer() {
		
	}
	

	public Customer(int id, String name, String userName, String email, String role, String userPassword,
			Address address) {
		super(id, name, userName, email, role, userPassword);
		this.address = address;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	


}
