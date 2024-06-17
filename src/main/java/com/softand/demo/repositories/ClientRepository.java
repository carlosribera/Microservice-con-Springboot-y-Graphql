package com.softand.demo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.softand.demo.models.Client;

public interface ClientRepository extends MongoRepository<Client, String> {
}


