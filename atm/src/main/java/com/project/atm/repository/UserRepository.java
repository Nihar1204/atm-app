package com.project.atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.atm.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	
}
