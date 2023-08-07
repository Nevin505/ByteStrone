package com.bytes.train.service;

import com.bytes.train.entities.UserDetails;

public interface UserService {

	UserDetails loginAccess(String userName,String userPassword) throws Exception;

}
