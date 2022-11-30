package com.example.mytwitter_backend.controller;

import com.example.mytwitter_backend.JWTHelper.JWTUtil;
import com.example.mytwitter_backend.model.JwtRequest;
import com.example.mytwitter_backend.model.JwtResponse;
import com.example.mytwitter_backend.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
       System.out.println("JWT request for user "+jwtRequest);
       try{
              this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),jwtRequest.getPassword()));
       }catch(UsernameNotFoundException u){
          u.printStackTrace();
          throw new Exception("Bad Credentials ");
       }catch(BadCredentialsException b){
           b.printStackTrace();
           throw new Exception("Bad Credentials ");
       }

       UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
       String token = this.jwtUtil.generateToken(userDetails);
       System.out.println("JWT"+ token);

       return ResponseEntity.ok(new JwtResponse(token));
    }
}
