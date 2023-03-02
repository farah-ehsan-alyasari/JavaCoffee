package com.javacoffee.JavaCoffee.adminPackage.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.Transient;

@Getter
@Setter
@NoArgsConstructor
public class ItemDTO {

    private Long id;

    private String name;

    private String category;

    private double listPrice;

    private double ourPrice;

    private boolean active = true;

    private String description;

    private int inStockNumber;

    private MultipartFile itemImage;

}
