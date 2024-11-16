package com.project.atm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.atm.entity.User;
import com.project.atm.exception.ResourceNotFoundException;
import com.project.atm.repository.UserRepository;

@Service
public class UserService {

	@Autowired
    private UserRepository userRepository;

    public User getUserById(int userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }
}
