package com.softand.demo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.softand.demo.models.Supplier;

public interface SupplierRepository extends MongoRepository<Supplier, String> {
}
