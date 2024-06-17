package com.softand.demo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "sale_details")
public class SaleDetail {

    @Id
    private String id;

    @NotBlank(message = "Sale ID is required")
    private String saleId;

    @NotBlank(message = "Product ID is required")
    private String productId;

    @NotNull(message = "Quantity is required")
    private Integer quantity;

    @NotNull(message = "Product price is required")
    private Double productPrice;

    @NotBlank(message = "Work order ID is required")
    private String workOrderId;
}
