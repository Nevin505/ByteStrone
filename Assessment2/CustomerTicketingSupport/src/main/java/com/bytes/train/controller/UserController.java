package com.bytes.train.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bytes.train.entities.Response;
import com.bytes.train.entities.UserDetails;
import com.bytes.train.service.UserService;
@RestController
@RequestMapping("/userLogin")
@CrossOrigin("http://localhost:4200")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/Access")
	public ResponseEntity<Response> checkLogin(@RequestBody UserDetails userDetails) {
		try {  
			String userName = userDetails.getUserName(); 
			String password = userDetails.getUserPassword();
			UserDetails response = userService.loginAccess(userName, password);
			if (response == null) { 
				return ResponseEntity.ok(new Response("Invalid Login", null, false));
			} 
			return ResponseEntity.ok(new Response("Login Successfull", response, true));
		} catch (Exception e) {
//		      e.printStackTrace();
			return ResponseEntity.ok(new Response(e.getMessage(), null, false));
		}
	}
	

}
