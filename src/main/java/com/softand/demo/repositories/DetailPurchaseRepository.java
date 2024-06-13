package com.softand.demo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.softand.demo.models.DetailPurchase;

public interface DetailPurchaseRepository extends MongoRepository<DetailPurchase, String> {
}
