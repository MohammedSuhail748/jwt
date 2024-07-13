package com.example.jwt.Contoller;

import com.example.jwt.Service.Impl.JwtServiceImpl;
import com.example.jwt.config.JwtUtilities;
import com.example.jwt.model.JwtRequest;
import com.example.jwt.model.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class JwtController {


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtServiceImpl service;

    @Autowired
    JwtUtilities jwtUtilities;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody JwtRequest jwtRequest) {

        JwtResponse jwtResponse = new JwtResponse();

        authenticate(jwtRequest.getName(), jwtRequest.getPassword());
        UserDetails user = service.loadUserByUsername(jwtRequest.getName());

        String token = jwtUtilities.GenerateToken(user.getUsername());
        System.out.println("token -------->" + token);
        jwtResponse.setToken(token);

        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }

    private void authenticate(String username, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
        try {
            authenticationManager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }


    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() throws Exception{
        return "Credentials Invalid !!";
    }

}
