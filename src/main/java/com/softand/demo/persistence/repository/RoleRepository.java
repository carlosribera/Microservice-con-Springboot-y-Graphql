package com.softand.demo.persistence.repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.softand.demo.persistence.entity.RoleEntity;
import com.softand.demo.persistence.entity.RoleEnum;

import java.util.List;

public interface RoleRepository extends MongoRepository<RoleEntity, String>{
    RoleEntity findByRoleName(RoleEnum roleName);
    List<RoleEntity> findRoleEntitiesByRoleNameIn(List<String> roleNames);
}
