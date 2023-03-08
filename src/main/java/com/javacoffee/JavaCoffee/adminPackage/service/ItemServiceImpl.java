package com.javacoffee.JavaCoffee.adminPackage.service;

import com.javacoffee.JavaCoffee.adminPackage.DTO.ItemDTO;
import com.javacoffee.JavaCoffee.adminPackage.entity.Item;
import com.javacoffee.JavaCoffee.adminPackage.exception.ItemNotFoundException;
import com.javacoffee.JavaCoffee.adminPackage.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ItemServiceImpl implements ItemService{

    @Autowired
    private ItemRepository itemRepository;

    /*The method annotation @Transactional is used to specify that the annotated method
    should be executed within a transactional context. This means that if any database
    operations are performed within the method, they will be executed as part of a transaction,
    which ensures that either all the operations are committed to the database together, or none of them are.*/
    @Transactional
    public void create(ItemDTO itemDTO){
        /*the ModelMapper class is used to map data between different Java classes,
        and in the code snippet provided, it is used to map data from a ItemDTO object to a new Item object,
        with a strict matching strategy for property mapping.*/
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Item item = modelMapper.map(itemDTO, Item.class);

        log.info("new item was created");
        itemRepository.save(item);

        /*Save the image locally.
        Image is not saved to Db,
        as explained why in Item Entity class*/
        MultipartFile itemImage = item.getItemImage();
        try{
            byte[] bytes = itemImage.getBytes();
            String str = item.getId() + ".png";
            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(new File("src/main/resources/static/images/items/"+str)));
            stream.write(bytes);
            stream.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }


    /*In this implementation, the findById() method of the itemRepository returns an Optional<Item> object,
    which may or may not contain an Item entity. We check if the itemOptional contains an Item by calling isPresent().
    If it does, we get the Item from the itemOptional using get() and return it. If it doesn't,
    we throw a RuntimeException with a message indicating that the item was not found.*/
    @Override
    public Item findOne(Long id) {
        Optional<Item> itemOptional = itemRepository.findById(id);
        if (itemOptional.isPresent()) {
            return itemOptional.get();
        } else {
            throw new ItemNotFoundException(id);
        }
    }

    @Transactional
    @Override
    public void update(ItemDTO itemDTO) {

        /*the ModelMapper class is used to map data between different Java classes,
        and in the code snippet provided, it is used to map data from a ItemDTO object to a new Item object,
        with a strict matching strategy for property mapping.*/
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            Item item = modelMapper.map(itemDTO, Item.class);

            itemRepository.save(item);

        MultipartFile itemImage = item.getItemImage();

        /*Update the image if needs to be updated*/
        if(!itemImage.isEmpty()){
            try{
                byte[] bytes = itemImage.getBytes();
                String str = item.getId() + ".png";

                /*Delete the old image that belonged to this item*/
                Files.delete(Paths.get("src/main/resources/static/images/items/"+str));

                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(new File("src/main/resources/static/images/items/"+str)));
                stream.write(bytes);
                stream.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        log.info("an item was updated with id: " + item.getId());
    }

    @Transactional
    @Override
    public void delete(Item item) {
        itemRepository.delete(item);
    }
}
