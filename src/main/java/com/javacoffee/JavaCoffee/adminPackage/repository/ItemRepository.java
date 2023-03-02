package com.javacoffee.JavaCoffee.adminPackage.repository;

import com.javacoffee.JavaCoffee.adminPackage.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
