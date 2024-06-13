package com.softand.demo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "detailPurchase")
public class DetailPurchase {
    @Id
    private String id;
    private String idProducto;
    private int cantidad;
    private double precioProducto;

}
