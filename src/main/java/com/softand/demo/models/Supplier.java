package com.softand.demo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "suppliers")
public class Supplier {
    @Id
    private String id;
    private String name;
    private String nit;
    private String telefono;

}
