package com.softand.demo.controller;

import com.softand.demo.common.exception.ProductNotFoundException;
import com.softand.demo.persistence.entity.ProductEntity;
import com.softand.demo.persistence.repository.ProductRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;


    @PostMapping
    public ResponseEntity<ProductEntity> createProduct(@Valid @RequestBody ProductEntity productEntity) {
        return new ResponseEntity<ProductEntity>(this.productRepository.save(productEntity), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductEntity> getProductById(@PathVariable String id) {
        return new ResponseEntity<>(this.productRepository.findById(id)
            .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id))
        , HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductEntity>> getAllProducts() {
        return new ResponseEntity<>(this.productRepository.findAll(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductEntity> updateProduct(@PathVariable String id, @Valid @RequestBody ProductEntity productDTO) {
        productDTO.setId(id);
        return new ResponseEntity<>(this.productRepository.save(productDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}