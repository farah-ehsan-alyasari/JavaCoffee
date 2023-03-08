package com.javacoffee.JavaCoffee.adminPackage.exception;

public class ItemNotFoundException extends RuntimeException{
    public ItemNotFoundException() {
        super("The item you tried to find was not found.");
    }

    public ItemNotFoundException(Long id) {
        super("Item not found with id " + id);
    }
}
