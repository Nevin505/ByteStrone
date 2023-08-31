package com.bytes.train.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import com.bytes.train.entities.Response;
import com.bytes.train.entities.UserDetails;
import com.bytes.train.execptions.ResourceNotFoundException;
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
			if(response==null) {
				return ResponseEntity.ok(new Response("Login Unsccessfull", null, false));
			}
			return ResponseEntity.ok(new Response("Login Successfull", response, true));
		}
			catch (ResourceNotFoundException e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage(), null, false));
			}
		 catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(e.getMessage(), null, false));
		}
	}
}
	


