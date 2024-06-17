package com.softand.demo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.softand.demo.models.Sale;

public interface SaleRepository extends MongoRepository<Sale, String> {
}
