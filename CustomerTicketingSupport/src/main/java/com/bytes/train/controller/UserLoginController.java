package com.bytes.train.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bytes.train.entities.UserLogin;
import com.bytes.train.service.UserLoginService;

@RestController
@RequestMapping("login")
public class UserLoginController {
	@Autowired
	UserLoginService userLoginService;

	@PostMapping("/login")
	public String getUserdetails(@RequestBody UserLogin userLogin) {
		String userName=userLogin.getUserName();
		String password=userLogin.getPassword();
		
		System.out.println("Hai="+userName);
         return  userLoginService.checkAccess(userName, password);

	}

}
