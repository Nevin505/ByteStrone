package com.bytes.train.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bytes.train.entities.UserLogin;
import com.bytes.train.repos.UserLoginDao;
import com.bytes.train.service.UserLoginService;
@Service
class UserloginServiceImpl implements UserLoginService {
	
	@Autowired
	UserLoginDao userLoginDao;
	@Override
	public String checkAccess(String userName, String password) {
		
		List<UserLogin> userdata=userLoginDao.findAll();
		for (UserLogin userLogin : userdata) {
			if((userLogin.getUserName().equals(userName))&&(userLogin.getPassword().equals(password))) {
				
				return "UserLogged in";
			}
		}
		return  "Invalid Id";

	}
}