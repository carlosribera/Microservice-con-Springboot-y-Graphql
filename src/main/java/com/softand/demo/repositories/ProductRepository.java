package com.softand.demo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.softand.demo.models.Product;


public interface ProductRepository extends MongoRepository<Product, String> {
}
