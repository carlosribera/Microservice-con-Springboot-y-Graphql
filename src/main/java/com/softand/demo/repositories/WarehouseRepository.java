package com.softand.demo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.softand.demo.models.Warehouse;

public interface WarehouseRepository extends MongoRepository<Warehouse, String> {
}
