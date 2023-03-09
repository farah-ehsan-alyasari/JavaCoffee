package com.javacoffee.JavaCoffee.securityPackageTest;

import com.javacoffee.JavaCoffee.JavaCoffeeApplication;
import com.javacoffee.JavaCoffee.securityPackage.entity.Role;
import com.javacoffee.JavaCoffee.securityPackage.repository.RoleRepository;
import com.javacoffee.JavaCoffee.securityPackage.service.RoleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = JavaCoffeeApplication.class)
public class RoleServiceImplTest {

    @Autowired
    RoleService roleService;

    @Autowired
    RoleRepository roleRepository;

    @Test
    public void saveRoleTest(){
        Role sampleRole = new Role("ROLE_GUEST");
        roleService.saveRole(sampleRole);

        Assertions.assertEquals(roleRepository.findRoleByName("ROLE_GUEST").getId(), sampleRole.getId());

        //delete sample data
        roleRepository.delete(sampleRole);
    }

}
