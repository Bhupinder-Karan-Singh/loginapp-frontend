package com.example.mytwitter_backend.controller;

import com.example.mytwitter_backend.JWTHelper.JWTUtil;
import com.example.mytwitter_backend.model.JwtRequest;
import com.example.mytwitter_backend.model.User;
import com.example.mytwitter_backend.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HomeController {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping("/getUsers")
    public User getUser(@RequestParam(required = false) String idToken){
        String username = this.jwtUtil.extractUsername(idToken);
        return customUserDetailsService.findUserByUsername(username);
    }

    @GetMapping("/search/{query}")
    public List<User> getUsers(@PathVariable(name="query") String search){
        System.out.println("search query by user ::::::"+ search);
        return customUserDetailsService.findUsers(search);
    }

    @PostMapping("/signup")
    public User registerUser(@RequestBody User user){
        return customUserDetailsService.registerUser(user);
    }
}
