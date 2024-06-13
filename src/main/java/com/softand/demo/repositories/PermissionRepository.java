package com.softand.demo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.softand.demo.models.PermissionEntity;

public interface PermissionRepository extends MongoRepository<PermissionEntity, String> {
}
