package com.softand.demo.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.softand.demo.persistence.entity.PermissionEntity;

public interface PermissionRepository extends MongoRepository<PermissionEntity, String> {
}
