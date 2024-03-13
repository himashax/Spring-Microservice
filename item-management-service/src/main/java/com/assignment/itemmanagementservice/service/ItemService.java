package com.assignment.itemmanagementservice.service;

import com.assignment.itemmanagementservice.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    Item addItem(Item item);

    Optional<Item> getItemById(Long itemId);

}
