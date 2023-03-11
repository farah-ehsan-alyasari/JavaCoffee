package com.javacoffee.JavaCoffee.shoppingCartPackage;

import com.javacoffee.JavaCoffee.JavaCoffeeApplication;
import com.javacoffee.JavaCoffee.securityPackage.entity.User;
import com.javacoffee.JavaCoffee.shoppingCartPackage.entity.CartItem;
import com.javacoffee.JavaCoffee.shoppingCartPackage.repository.CartItemsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = JavaCoffeeApplication.class)
public class ShoppingCartWorkingTests {

    @Autowired
    CartItemsRepository cartRepo;

    @Test
    public void testGetCartItemByUser(){
        User user = new User();
        user.setId(5L);

        List<CartItem> cartItems = cartRepo.findByUser(user);

        Assertions.assertEquals(2, cartItems.size());
    }

}
