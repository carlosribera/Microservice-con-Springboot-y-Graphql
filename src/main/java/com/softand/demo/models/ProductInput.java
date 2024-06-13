package com.softand.demo.models;

import lombok.Data;

@Data
public class ProductInput {
    private String id;
    private String name;
    private Double price;
    private String imageUrl;
    private String description;
}
