package com.softand.demo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.softand.demo.models.SaleDetail;

public interface SaleDetailRepository extends MongoRepository<SaleDetail, String> {
}
