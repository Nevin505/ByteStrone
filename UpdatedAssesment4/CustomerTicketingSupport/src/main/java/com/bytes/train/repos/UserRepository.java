package com.bytes.train.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bytes.train.entities.UserDetails;

public interface UserRepository extends  JpaRepository<UserDetails,Integer>{
	
	@Query("SELECT u FROM UserDetails u WHERE u.userName= :userName")
	UserDetails findByUserName(String userName);

}
