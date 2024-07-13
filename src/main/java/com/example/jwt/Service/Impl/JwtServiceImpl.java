package com.example.jwt.Service.Impl;

import com.example.jwt.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtServiceImpl implements UserDetailsService {


    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDetails userDetails = userService.findByName(username);
        if (userDetails == null)
            throw new UsernameNotFoundException("User not found");

        return userDetails;
    }
}
