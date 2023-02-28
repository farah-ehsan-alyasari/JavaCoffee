package com.javacoffee.JavaCoffee;

import com.javacoffee.JavaCoffee.entity.Role;
import com.javacoffee.JavaCoffee.entity.User;
import com.javacoffee.JavaCoffee.repository.UserRepository;
import com.javacoffee.JavaCoffee.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class JavaCoffeeApplication implements CommandLineRunner {

	@Autowired
	RoleService roleService;

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(JavaCoffeeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		initRoles();
		initUsers();
	}

	private void initRoles()
	{
		roleService.saveRole(new Role("ROLE_ADMIN"));
		roleService.saveRole(new Role("ROLE_USER"));
		//roleService.saveRole(new Role("ROLE_GUEST"));
	}

	private void initUsers()
	{
		//Login 'admin@admin.com'
		//Password 'test'
		userRepository.save(new User("admin", "Admin", "Admin", "admin@admin.com", "998-098-0987",
				"12345", "$2a$11$DZfZLO720bZby.1QWCu81.gg2BUYCJC7PSsjEUMho.ZaVUVC1h9ZC"));

		User user=  userRepository.findUserByEmail("admin@admin.com");
		user.setRoles(Arrays.asList(roleService.findRoleByRoleName("ROLE_ADMIN")));

		userRepository.save(user);


	}


}
