package com.softand.demo.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.softand.demo.models.Usuario;

public interface UserRepository extends MongoRepository<Usuario, String>{
    Optional<Usuario> findByUsername(String username);
}
