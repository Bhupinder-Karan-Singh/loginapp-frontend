package com.example.mytwitter_backend.repository;

import com.example.mytwitter_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.username = ?1")
    public User findUserByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.firstname LIKE %?1% ")
    List<User> findAllUsers(String search);
}
