package com.javacoffee.JavaCoffee.service;

import com.javacoffee.JavaCoffee.DTO.UserDTO;
import com.javacoffee.JavaCoffee.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public UserDetails loadUserByUsername(String userName);
    public void creat(UserDTO userDTO);
    public User findUserByEmail(String email);
    public User findUserByName(String name);
}