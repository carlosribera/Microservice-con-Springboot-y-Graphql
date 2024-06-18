package com.softand.demo.models;

import lombok.Data;

@Data
public class InventoryInput {
    private String id;
    private String warehouseId;
    private String productId;
    private Integer quantity;
    private String section;
}

