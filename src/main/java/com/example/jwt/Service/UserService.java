package com.example.jwt.Service;

import com.example.jwt.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    User saveUser(User user);

    UserDetails findByName(String username);
}
