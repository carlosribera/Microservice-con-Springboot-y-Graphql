package com.softand.demo.models;

import lombok.Data;

@Data
public class SaleDetailInput {
    private String id;
    private String saleId;
    private String productId;
    private Integer quantity;
    private Double productPrice;
    private String workOrderId;
}

