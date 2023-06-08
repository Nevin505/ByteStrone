package com.bytes.train.service;

import org.springframework.http.ResponseEntity;

public interface UserLoginService{
	
	  
	public ResponseEntity<Integer> checkAccess(String userName,String password);
	

}
