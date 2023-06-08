package com.bytes.train.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bytes.train.entities.Customer;
import com.bytes.train.repos.CustomerRepository;
import com.bytes.train.service.UserLoginService;
@Service
class UserloginServiceImpl implements UserLoginService {
	
	@Autowired
	CustomerRepository customerDao;
	int id;
	

	@Override
	public ResponseEntity<Integer> checkAccess(String userName, String password) {
		List<Customer> userdata=customerDao.findAll();
		for (Customer userLogin : userdata) {
			if((userLogin.getUsername().equals(userName))&&(userLogin.getUserpassword().equals(password))) {				
				return ResponseEntity.ok(userLogin.getCustomerid());
			}
		}
		return ResponseEntity.ok(0);

	}
}
	
	