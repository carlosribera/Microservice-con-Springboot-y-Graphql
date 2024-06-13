package com.softand.demo.repositories;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.softand.demo.models.Role;
import com.softand.demo.models.RoleEnum;

import java.util.List;

public interface RoleRepository extends MongoRepository<Role, String>{
    Role findByRoleName(RoleEnum roleName);
    List<Role> findRoleEntitiesByRoleNameIn(List<String> roleNames);
}
