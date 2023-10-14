package com.bytes.train.controller;

import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bytes.train.entities.Response;
import com.bytes.train.entities.UserDetails;
import com.bytes.train.execptions.ResourceNotFoundException;
import com.bytes.train.service.UserService;
@RestController
@RequestMapping("/userLogin")
@CrossOrigin("http://localhost:4200")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	
	@PostMapping("/Access")
	public ResponseEntity<Response> checkLogin(@RequestBody UserDetails userDetails) {
		try {   
			String userName = userDetails.getUserName(); 
			String password = userDetails.getUserPassword();
			byte[] decodedBytes = Base64.getDecoder().decode(password);
			String decodedPassword = new String(decodedBytes);
			UserDetails response = userService.loginAccess(userName,decodedPassword);
			if(response==null) {
				return ResponseEntity.ok(new Response("Login Unsccessfull", null, false));
			} 
			return ResponseEntity.ok(new Response("Login Successfull", response, true));
		}
			catch (ResourceNotFoundException e) {
				 logger.error("The Issue is",e);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Login Unsccessfull", null, false));
			}
		 catch (Exception e) {
			 logger.error("The Issue is",e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Some Error Occurred", null, false));
		}
	}
}
	


