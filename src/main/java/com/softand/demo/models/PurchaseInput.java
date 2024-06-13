package com.softand.demo.models;

import lombok.Data;

@Data
public class PurchaseInput {
    private String id;
    private String idCliente;
    private double precioTotal;
}

