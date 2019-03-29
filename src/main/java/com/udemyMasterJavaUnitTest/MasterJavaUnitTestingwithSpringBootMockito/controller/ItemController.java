package com.udemyMasterJavaUnitTest.MasterJavaUnitTestingwithSpringBootMockito.controller;

import com.udemyMasterJavaUnitTest.MasterJavaUnitTestingwithSpringBootMockito.model.Item;
import com.udemyMasterJavaUnitTest.MasterJavaUnitTestingwithSpringBootMockito.servis.ItemBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private ItemBusinessService businessService;

    @GetMapping("/dummy-item")
    public Item dummyItem() {
        return new Item(1, "Ball", 10, 100);
    }

    @GetMapping("/item-from-business-service")
    public Item itemFromBusinessService() {

        return businessService.retreiveHardcodedItem();
    }


    @GetMapping("/all-items-from-database")
    public List<Item> retrieveAllItems() {

        return businessService.retrieveAllItems();
    }
}

