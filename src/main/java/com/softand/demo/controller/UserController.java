package com.softand.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softand.demo.models.Usuario;
import com.softand.demo.models.dto.RegisterInput;
import com.softand.demo.models.dto.LoginInput;
import com.softand.demo.models.dto.AuthResponse;
import com.softand.demo.repositories.UserRepository;
import com.softand.demo.service.UserDetailServiceImpl;

@RestController
public class UserController {

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private UserRepository userRepository;

    @QueryMapping
    public List<Usuario> usuarios(){
        return this.userRepository.findAll();
    }
    
    @MutationMapping
    public AuthResponse register(@Argument RegisterInput registerInput){
        return this.userDetailService.createUser(registerInput);
    }

    @MutationMapping
    public AuthResponse login(@Argument LoginInput loginInput){
        return this.userDetailService.loginUser(loginInput);
    }
}
