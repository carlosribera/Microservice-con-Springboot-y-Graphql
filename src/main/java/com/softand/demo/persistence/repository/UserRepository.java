package com.softand.demo.persistence.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.softand.demo.persistence.entity.UserEntity;

public interface UserRepository extends MongoRepository<UserEntity, String>{
    Optional<UserEntity> findByUsername(String username);
}
