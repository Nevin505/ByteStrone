package com.bytes.train.loginauthentication;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/credentials")
public class AuthenicationController {
	@Autowired   
	AuthService auth;
//	auth.getUser();
	@PostMapping("/login")
	public String Userlogin(@RequestBody AuthenticationEntity Authentit) {
		return auth.getUser(Authentit.username,Authentit.password);
		
	}
	
}
