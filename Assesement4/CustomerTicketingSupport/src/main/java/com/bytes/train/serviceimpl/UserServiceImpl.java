package com.bytes.train.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bytes.train.entities.UserDetails;
import com.bytes.train.execptions.ResourceNotFoundException;
import com.bytes.train.repos.UserRepository;
import com.bytes.train.service.UserService;

@Service
public class UserServiceImpl implements UserService{
    
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loginAccess(String userName, String userPassword) throws Exception  {
		System.out.println("Here");
		UserDetails userDetails=userRepository.findByUserName(userName);
		if(userDetails==null) { 
			throw new ResourceNotFoundException("Invlaid Login"); 
		}
		if ((userDetails.getUserName().equals(userName)) && (userDetails.getUserPassword().equals(userPassword))) {
			return userDetails;
		}
		
		return null;
	
	}

	

}
