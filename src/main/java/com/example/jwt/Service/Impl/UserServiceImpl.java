package com.example.jwt.Service.Impl;

import com.example.jwt.Repository.UserRepository;
import com.example.jwt.Service.UserService;
import com.example.jwt.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public UserDetails findByName(String username) {
        return userRepository.findByEmail(username);
    }
}
