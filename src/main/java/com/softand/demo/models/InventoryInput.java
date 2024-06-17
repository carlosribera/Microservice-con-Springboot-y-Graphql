package com.softand.demo.models;

import lombok.Data;

@Data
public class InventoryInput {
    private String id;
    private Integer warehouseId;
    private Integer productId;
    private Integer quantity;
    private String section;
}

