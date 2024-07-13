package com.example.jwt.Contoller;

import com.example.jwt.Service.UserService;
import com.example.jwt.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/get")
    public String userName() {
        return "suhail";
    }

    @PostMapping("/save")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User user1 =  userService.saveUser(user);
        return new ResponseEntity<>(user1, HttpStatus.OK);
    }
}
