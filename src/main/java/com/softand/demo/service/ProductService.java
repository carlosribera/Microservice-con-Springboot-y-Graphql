package com.softand.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.softand.demo.common.exception.ProductNotFoundException;
import com.softand.demo.controller.EntityResponse;
import com.softand.demo.models.Product;
import com.softand.demo.repositories.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product getProductById(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + id));
        log.info("found {} products by id {}", product.getId(), id);
        return product;
    }

    public List<Product> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        if (CollectionUtils.isEmpty(productList)) {
            log.info("Product not found");
            return null;
        }
        return productList;
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public EntityResponse updateProduct(String id, Product productEntity) {
        Product product = getProductById(id);

        product.setName(productEntity.getName());
        product.setImageUrl(productEntity.getImageUrl());
        product.setPrice(productEntity.getPrice());
        product.setDescription(productEntity.getDescription());

        EntityResponse responseEntity = new EntityResponse(product.getName(), "Producto Actualizado", true);

        productRepository.save(product);
        return responseEntity;
    }

    public String deleteById(String id) {
        Product product = getProductById(id);
        this.productRepository.deleteById(product.getId());
        log.info("Product by id {} deleted.", id);
        return "Product by id: " + id + " deleted.";
    }
}
