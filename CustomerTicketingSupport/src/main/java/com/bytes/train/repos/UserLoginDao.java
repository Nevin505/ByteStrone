package com.bytes.train.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bytes.train.entities.UserLogin;

public interface UserLoginDao extends JpaRepository<UserLogin, Integer>{
	
}