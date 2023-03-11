package com.javacoffee.JavaCoffee.shoppingCartPackage;

import com.javacoffee.JavaCoffee.JavaCoffeeApplication;
import com.javacoffee.JavaCoffee.adminPackage.entity.Item;
import com.javacoffee.JavaCoffee.securityPackage.entity.User;
import com.javacoffee.JavaCoffee.shoppingCartPackage.entity.CartItem;
import com.javacoffee.JavaCoffee.shoppingCartPackage.repository.CartItemsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.assertTrue;


/*Those test do not work*/
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ShoppingCartTests {

    @Autowired
    private CartItemsRepository cartRepo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testAddOneCartItem(){
        Item item = entityManager.find(Item.class, 32);
        User user = entityManager.find(User.class, 5);

        CartItem cartItem = new CartItem();
        cartItem.setUser(user);
        cartItem.setItem(item);
        cartItem.setQuantity(1);

        cartRepo.save(cartItem);

        assertTrue(cartItem.getId()>0);
    }

}
