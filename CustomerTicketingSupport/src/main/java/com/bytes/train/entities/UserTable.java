package com.bytes.train.entities;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table
public class UserTable {
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
	@SequenceGenerator(name = "customer_seq", sequenceName = "customer_seq", initialValue = 100, allocationSize = 1)
	int userId;
	String password;2u7
	String userName;
	String role;
	String email;
	long phoneNumber;
//	public UserTable(){
//		
//	}
//	public UserTable(int userId, String password, String userName, String role, String email, long phoneNumber) {
//		super();
//		this.userId = userId;
//		this.password = password;
//		this.userName = userName;
//		this.role = role;
//		this.email = email;
//		this.phoneNumber = phoneNumber;
//	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return userName;
	}
	public void setUsername(String username) {
		userName = username;
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
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	

}
