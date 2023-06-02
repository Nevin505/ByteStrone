package com.bytes.train.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bytes.train.entities.UserTable;
import com.bytes.train.repos.LoginRepo;
import com.bytes.train.service.loginService;

@Service
public class loginServiceImpl implements loginService {

	@Autowired
	LoginRepo loginRepo;

	public String checkAccess(String userName,String password) {
    List<UserTable>userList=loginRepo.findAll();
    System.out.println(userName);
    System.out.println(userName);
    System.out.println(password);
	for(UserTable user:userList) {
	if(user.getEmail().equals(userName)&&(user.getPassword().equals(password))){
	return user.getRole();
	}
	}
	return "Invalid User Id";
	}

}
