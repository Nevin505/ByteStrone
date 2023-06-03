package com.bytes.train.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bytes.train.entities.UserTable;

public interface LoginRepo extends JpaRepository<UserTable,Integer>{

}
