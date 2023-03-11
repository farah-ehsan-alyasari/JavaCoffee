package com.javacoffee.JavaCoffee.shoppingCartPackage.entity;

import com.javacoffee.JavaCoffee.adminPackage.entity.Item;
import com.javacoffee.JavaCoffee.securityPackage.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="cart_items")
@Getter
@Setter
@NoArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    //a product can have one or more cart items
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    //a customer can choose one or more items into shopping cart
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Integer quantity;
}
