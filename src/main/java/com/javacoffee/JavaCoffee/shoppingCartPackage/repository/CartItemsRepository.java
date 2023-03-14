package com.javacoffee.JavaCoffee.shoppingCartPackage.repository;

import com.javacoffee.JavaCoffee.adminPackage.entity.Item;
import com.javacoffee.JavaCoffee.securityPackage.entity.User;
import com.javacoffee.JavaCoffee.shoppingCartPackage.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartItemsRepository extends JpaRepository<CartItem, Integer> {

    public List<CartItem> findByUser(User user);

    public CartItem findByUserAndItem(User user, Item item);

    @Modifying
    @Query("UPDATE CartItem c SET c.quantity = :quantity WHERE c.item.id = :itemId AND c.user.id = :userId")
    public void updateQuantity(@Param("quantity") Integer quantity, @Param("itemId") Long itemId, @Param("userId") Long userId);

}
