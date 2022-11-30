package com.example.mytwitter_backend.service;

import com.example.mytwitter_backend.model.CustomUserDetail;
import com.example.mytwitter_backend.model.User;
import com.example.mytwitter_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = this.userRepository.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("User not found");
        } else{
            return new CustomUserDetail(user);
        }
    }

    public User registerUser(User user) {
         return userRepository.save(user);
    }

    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public List<User> findUsers(String search) {return userRepository.findAllUsers(search);}
}
