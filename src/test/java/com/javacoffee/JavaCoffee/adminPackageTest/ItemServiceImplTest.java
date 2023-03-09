package com.javacoffee.JavaCoffee.adminPackageTest;

import com.javacoffee.JavaCoffee.JavaCoffeeApplication;
import com.javacoffee.JavaCoffee.adminPackage.entity.Item;
import com.javacoffee.JavaCoffee.adminPackage.repository.ItemRepository;
import com.javacoffee.JavaCoffee.adminPackage.service.ItemService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = JavaCoffeeApplication.class)
public class ItemServiceImplTest {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemService itemService;

    /*@Test
    public void testDeleteItem(){

        //get information about current size of database
        List<Item> result = itemRepository.findAll();
        int resultSizeBefore = result.size();

        //create some sample data
        Item item1 = new Item(1L, "item1");

        //save the items
        itemRepository.save(item1);

        //use the method being tested
        itemService.delete(item1);

        //verify that the item was deleted
        List<Item> resultAfter = itemRepository.findAll();
        Assertions.assertEquals(resultSizeBefore, resultAfter.size());

    }*/

    @Test
    public void findOneByIdTest(){
        //create a sample item
        Item testItem = new Item("testItem");

        itemRepository.save(testItem);

        Long testItemId = testItem.getId();

        //retrieve it from the database by its id
        Item verifiedItem = itemService.findOne(testItemId);

        //verify equalness
        Assertions.assertEquals(testItem.getName(), verifiedItem.getName());
        Assertions.assertEquals(testItem.getId(), verifiedItem.getId());

        //delete the test item from database
        itemRepository.delete(testItem);
    }

}
