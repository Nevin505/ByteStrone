package com.bytes.train.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bytes.train.entities.UserTable;
import com.bytes.train.service.loginService;

@RestController
@RequestMapping("login")
public class UserController {
	@Autowired
	loginService loginservice;

	@PostMapping("id")
	public String getUserdetails(@RequestBody UserTable userTable) {
		String userName=userTable.getEmail();
		String password=userTable.getPassword();
		System.out.println("Hai="+userName);
         return loginservice.checkAccess(userName,password);
//		return loginservice.loginDetails(userTable.getUsername(), userTable.getPassword());

	}

}
