package com.bytes.train.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bytes.train.entities.Customer;
import com.bytes.train.service.UserLoginService;

@RestController
@RequestMapping("/login")
@CrossOrigin("http://localhost:4200")
public class UserLoginController {
	
	@Autowired
	UserLoginService userLoginService;

   
	@PostMapping("/Access")
    public ResponseEntity<Integer> checkAccess(@RequestBody Customer customer) {

		String userName=customer.getUsername();
		String password=customer.getUserpassword();
		
		return userLoginService.checkAccess(userName, password);
       
    }
	

}
