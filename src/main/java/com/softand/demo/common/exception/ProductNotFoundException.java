package com.softand.demo.common.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String id) {
        super("Producto no encontrado con id: " + id);
    }
}
