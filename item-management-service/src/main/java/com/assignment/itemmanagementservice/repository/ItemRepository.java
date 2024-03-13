package com.assignment.itemmanagementservice.repository;

import com.assignment.itemmanagementservice.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
