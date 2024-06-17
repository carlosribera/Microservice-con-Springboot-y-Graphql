package com.softand.demo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.softand.demo.models.Inventory;

public interface InventoryRepository extends MongoRepository<Inventory, String> {
}
