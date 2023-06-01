package com.bytes.train.entities;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table

public class Username {
	@Id
	int userId;
	String password;
	String Username;
	String role;

}
