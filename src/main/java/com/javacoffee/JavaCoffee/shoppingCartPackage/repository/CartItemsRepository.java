package com.javacoffee.JavaCoffee.shoppingCartPackage.repository;

import com.javacoffee.JavaCoffee.adminPackage.entity.Item;
import com.javacoffee.JavaCoffee.securityPackage.entity.User;
import com.javacoffee.JavaCoffee.shoppingCartPackage.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartItemsRepository extends JpaRepository<CartItem, Integer> {

    public List<CartItem> findByUser(User user);

    public CartItem findByUserAndItem(User user, Item item);

    /*@Query("UPDATE cartItem c SET c.quantity = ?1 WHERE c.item.id = ?2"
    + "AND c.user.id=?3")
    public void updateQuantity(Integer quantity, Long itemId, Long userId);*/

}
