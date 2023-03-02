package com.javacoffee.JavaCoffee.adminPackage.service;

import com.javacoffee.JavaCoffee.adminPackage.DTO.ItemDTO;
import com.javacoffee.JavaCoffee.adminPackage.entity.Item;

import java.util.List;

public interface ItemService {
    void create(ItemDTO itemDTO);

    List<Item> findAll();
}
