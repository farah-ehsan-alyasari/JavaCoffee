package com.javacoffee.JavaCoffee.securityPackage.service;


import com.javacoffee.JavaCoffee.securityPackage.entity.Role;
import com.javacoffee.JavaCoffee.securityPackage.entity.User;
import com.javacoffee.JavaCoffee.securityPackage.repository.UserRepository;
import com.javacoffee.JavaCoffee.securityPackage.DTO.UserDTO;
import com.javacoffee.JavaCoffee.securityPackage.security.UserPrincipal;
import com.javacoffee.JavaCoffee.securityPackage.service.RoleService;
import com.javacoffee.JavaCoffee.securityPackage.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * There are two examples of using UserService with session based user authentication
 * in Spring
 *
 * @implNote
 * 1) UserPrincipal class which implements UserDetails interface.
 * This way you get more flexibility and control over user authorization and authentication process. For instance, set user account disabled/enabled and other
 * 2) commented out code is more simple and quick way make things work but there is lack
 * of control
 */
/**
 * @author Igor Adulyan
 */

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(userName);
        log.debug(userName);
        if (user == null) {
            log.warn("Invalid username or password {}", userName);

            throw new UsernameNotFoundException("Invalid username or password.");
        }
        /* return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),   mapRolesToAuthorities(user.getRoles()));*/
        return new UserPrincipal(user, roleService.getRolesByUser(user.getId()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }


    /**
     * Using model mapper helps to avoid extra coding
     * @param userDTO
     */
    @Transactional
    public void creat(UserDTO userDTO)
    {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        User user = modelMapper.map(userDTO, User.class);

        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList(roleService.findRoleByRoleName("ROLE_USER")));

        userRepository.save(user);
    }

    /**    * In this example login and email has the same values @param email @return
     */
    public User findUserByEmail(String email)
    {
        return userRepository.findUserByEmail(email);
    }

    public User findUserByName(String name)
    {
        return userRepository.findUserByUserName(name);
    }
}
