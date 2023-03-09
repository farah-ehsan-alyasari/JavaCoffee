package com.javacoffee.JavaCoffee.adminPackage.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.javacoffee.JavaCoffee.shoppingCartPackage.entity.ItemToCartItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String category;

    private double listPrice;

    private double ourPrice;

    //TODO: this line needs to be changed to not set a value
    //TODO: then you have to test the application without it
    private boolean active = true;

    /*when this entity is persisted to the database,
    the value of the description field will be stored in a column of type text.
    The text type is typically used for longer string values that can't fit in a varchar column.*/
    @Column(columnDefinition = "text")
    private String description;

    private int inStockNumber;

    /*Transient means this field wil not be stored in the database.
     When I try to store it in the database, I get an issue because MultipartImage
     is not a standard java type that hibernate knows how to map to a database type.
     another option, would be to store the image as a blob of binary*/
    @Transient
    private MultipartFile itemImage;

    //special constructor used for testing purposes
    public Item(String name){
        this.name = name;
    }


}
