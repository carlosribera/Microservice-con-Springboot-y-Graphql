package com.softand.demo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/method")
public class TestAuthController {

    @GetMapping("/get")
    public String helloGet(){ return "Hello world - GET";  }
    
    @GetMapping("/post")
    public String helloPost(){ return "Hello world - POST"; }

    @GetMapping("/put")
    public String helloPut(){  return "Hello world - PUT"; }

    @DeleteMapping("/delete")
    public String helloDelete(){ return "Hello World - DELETE";}

    @PatchMapping("/patch")
    public String helloPatch(){ return "Hello World - PATCH";}
}
