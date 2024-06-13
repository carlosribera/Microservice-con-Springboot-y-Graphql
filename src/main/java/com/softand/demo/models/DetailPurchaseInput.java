package com.softand.demo.models;

import lombok.Data;

@Data
public class DetailPurchaseInput {
    private String id;
    private String idProducto;
    private int cantidad;
    private double precioProducto;
}
