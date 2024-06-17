package com.softand.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import com.softand.demo.models.ProductInput;
import com.softand.demo.models.Product;
import com.softand.demo.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@PreAuthorize("denyAll()")
public class ProductController {
   
    @Autowired
    private ProductService productService;

    @QueryMapping
    @PreAuthorize("hasAuthority('READ')")
    public Product getProductById(@Argument String id) {
        log.info("Query product in GraphQL Server by id {}", id);
        return productService.getProductById(id);
    }
    
    @QueryMapping
    @PreAuthorize("permitAll()")
    public List<Product> getAllProducts() {
        return this.productService.getAllProducts();
    }
    
    @MutationMapping
    // @PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("permitAll()")
    public Product createProduct(@Argument ProductInput productInput) {
        Product product = new Product();
        product.setName(productInput.getName());
        product.setPrice(productInput.getPrice());
        product.setImageUrl(productInput.getImageUrl());
        product.setDescription(productInput.getDescription());

        return this.productService.createProduct(product);
    }

    @MutationMapping
    @PreAuthorize("hasRole('ADMIN')")
    public EntityResponse updateProduct(@Argument String id, @Argument ProductInput productInput) {
        
        Product product = new Product();
        product.setName(productInput.getName());
        product.setPrice(productInput.getPrice());
        product.setImageUrl(productInput.getImageUrl());
        product.setDescription(productInput.getDescription());

        return this.productService.updateProduct(id, product);
    }

    @MutationMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteProductById(@Argument String id) {
        this.productService.deleteById(id);
        return "Product with id: "+id+" eliminado correctamente.";
    }
}
