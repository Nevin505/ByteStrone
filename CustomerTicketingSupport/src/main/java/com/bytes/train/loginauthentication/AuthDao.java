package com.bytes.train.loginauthentication;



import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthDao extends JpaRepository<AuthenticationEntity, Integer> {

}
