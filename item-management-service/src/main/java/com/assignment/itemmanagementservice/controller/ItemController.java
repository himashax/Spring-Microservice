package com.assignment.itemmanagementservice.controller;

import com.assignment.itemmanagementservice.model.Item;
import com.assignment.itemmanagementservice.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/items")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping("/add")
    public Item saveItem(@RequestBody Item item) {
        return itemService.addItem(item);
    }

    @GetMapping("/{id}")
    public Item getItemById(@PathVariable("id") Long itemId) {
        return itemService.getItemById(itemId).orElse(null);
    }

}
