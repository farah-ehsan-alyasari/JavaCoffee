package com.javacoffee.JavaCoffee.shoppingCartPackage.service;

import com.javacoffee.JavaCoffee.adminPackage.entity.Item;
import com.javacoffee.JavaCoffee.adminPackage.repository.ItemRepository;
import com.javacoffee.JavaCoffee.securityPackage.entity.User;
import com.javacoffee.JavaCoffee.shoppingCartPackage.entity.CartItem;
import com.javacoffee.JavaCoffee.shoppingCartPackage.repository.CartItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ShoppingCartServices {

    @Autowired
    private CartItemsRepository cartRepo;

    @Autowired
    private ItemRepository itemRepo;

    public List<CartItem> listCartItems(User user){
        return cartRepo.findByUser(user);
    }

    //returns the quantity of products that have been added to the shopping cart
    public Integer addItem(Long itemId, Integer quantity, User user){
        Integer addedQuantity = quantity;

        Item item = itemRepo.findById(itemId).get();

       CartItem cartItem = cartRepo.findByUserAndItem(user, item);

       //the product has been added to Shopping cart for this customer, update quantity
       if(cartItem != null){
           addedQuantity = cartItem.getQuantity() + quantity;
           cartItem.setQuantity(addedQuantity);

           //update inStockNumber
           Integer currentInStockNumber = cartItem.getItem().getInStockNumber();
           cartItem.getItem().setInStockNumber( currentInStockNumber - quantity);
       }
       else{
           //the product has not been added to the shopping cart of this customer
           cartItem = new CartItem();
           cartItem.setQuantity(quantity);
           cartItem.setUser(user);
           cartItem.setItem(item);

           //update inStockNumber
           Integer currentInStockNumber = cartItem.getItem().getInStockNumber();
           cartItem.getItem().setInStockNumber(currentInStockNumber - quantity);

       }

       cartRepo.save(cartItem);

        return addedQuantity;
    }


    @Transactional
    public void updateQuantity(Long itemId, Integer quantity, User user){
        cartRepo.updateQuantity(quantity, itemId, user.getId());

        //update inStockNumber
        Item item = itemRepo.findById(itemId).get();
        Integer currentQuantity = cartRepo.findByUserAndItem(user, item).getQuantity();
        Integer currInStockNumber = item.getInStockNumber();
        if(quantity < currentQuantity){
            Integer change = currentQuantity - quantity;
            item.setInStockNumber(currInStockNumber-change);
        }
        if(quantity > currentQuantity){
            Integer change = quantity - currentQuantity;
            item.setInStockNumber((currInStockNumber+change));
        }
    }

    @Transactional
    public void removeItem(Long itemId, User user){
        cartRepo.deleteByUserAndItem(user.getId(), itemId);
    }
}
