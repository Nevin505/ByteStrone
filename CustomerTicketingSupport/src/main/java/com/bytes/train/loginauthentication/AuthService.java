package com.bytes.train.loginauthentication;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AuthService {
	
	@Autowired
	AuthDao auth1;
	public String getUser(String Username,String Password){
		List<AuthenticationEntity> credentials=auth1.findAll();
		for (AuthenticationEntity authenticationEntity : credentials) {
			if(authenticationEntity.username.equals(Username)&&authenticationEntity.password.equals(Password)){
				System.out.println("Coorect Password");
				return authenticationEntity.Role;
			}
		}
//		return credentials;
		return "Not Found";
		
	}
	

}
