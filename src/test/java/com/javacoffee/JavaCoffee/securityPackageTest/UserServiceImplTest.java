package com.javacoffee.JavaCoffee.securityPackageTest;

import com.javacoffee.JavaCoffee.JavaCoffeeApplication;
import com.javacoffee.JavaCoffee.securityPackage.entity.User;
import com.javacoffee.JavaCoffee.securityPackage.repository.UserRepository;
import com.javacoffee.JavaCoffee.securityPackage.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = JavaCoffeeApplication.class)

public class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Test
    public void findUserByEmailTest(){

        //add sample user
        User sampleUser = new User("sampleUN", "sample@email.com", "samplePW");

        //find user with repository first
        User sampleUserFromRepo = userRepository.findUserByEmail("sample@email.com");
        User sampleUserFromService = userService.findUserByEmail("sample@email.com");

        //check if they are equal
        Assertions.assertEquals(sampleUserFromRepo, sampleUserFromService);

        //delete the test data
        userRepository.delete(sampleUser);
    }

}
