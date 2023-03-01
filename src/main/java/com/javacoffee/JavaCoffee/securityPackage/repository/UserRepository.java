package com.javacoffee.JavaCoffee.securityPackage.repository;

import com.javacoffee.JavaCoffee.securityPackage.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Igor Adulyan
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User findUserByEmail(String email);
    public User findUserByUserName(String name);

}
