package com.javacoffee.JavaCoffee.securityPackage.service;

import com.javacoffee.JavaCoffee.securityPackage.entity.User;
import com.javacoffee.JavaCoffee.securityPackage.DTO.UserDTO;
import com.javacoffee.JavaCoffee.securityPackage.security.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Igor Adulyan
 */
public interface UserService extends UserDetailsService {
    public UserDetails loadUserByUsername(String userName);
    public void creat(UserDTO userDTO);
    public User findUserByEmail(String email);
    public User findUserByName(String name);

    public User getCurrentlyLoggedInUser(UserPrincipal userPrincipal);
}