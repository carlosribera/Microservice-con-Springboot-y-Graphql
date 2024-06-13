package com.softand.demo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "purchases")
public class Purchase {
    
    @Id
    private String id;
    private String idCliente;
    private double precioTotal;
}
