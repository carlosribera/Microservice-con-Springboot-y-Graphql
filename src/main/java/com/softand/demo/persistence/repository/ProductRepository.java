package com.softand.demo.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.softand.demo.persistence.entity.ProductEntity;

public interface ProductRepository extends MongoRepository<ProductEntity, String> {
}
