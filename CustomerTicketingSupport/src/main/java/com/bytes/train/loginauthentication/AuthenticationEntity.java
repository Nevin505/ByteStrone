package com.bytes.train.loginauthentication;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "username")
public class AuthenticationEntity {
	@Id
	int userId;
	String username;
	String password;
	String Role;

	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		Role = role;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
