package com.softand.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import com.softand.demo.models.PurchaseInput;
import com.softand.demo.models.Purchase;
import com.softand.demo.service.PurchaseService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@PreAuthorize("denyAll()")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @QueryMapping
    @PreAuthorize("hasAuthority('READ')")
    public Purchase purchase(@Argument String id) {
        log.info("Query compra in GraphQL Server by id {}", id);
        return purchaseService.getCompraById(id);
    }

    @QueryMapping
    @PreAuthorize("permitAll()")
    public List<Purchase> purchases() {
        return this.purchaseService.getAllCompras();
    }

    @MutationMapping
    @PreAuthorize("permitAll()")
    public Purchase addPurchase(@Argument PurchaseInput purchaseInput) {
        Purchase compra = new Purchase();
        compra.setIdCliente(purchaseInput.getIdCliente());
        compra.setPrecioTotal(purchaseInput.getPrecioTotal());

        return this.purchaseService.createPurchase(compra);
    }

    @MutationMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Purchase updatePurchase(@Argument String id, @Argument PurchaseInput purchaseInput) {
        Purchase compra = new Purchase();
        compra.setId(id);
        compra.setIdCliente(purchaseInput.getIdCliente());
        compra.setPrecioTotal(purchaseInput.getPrecioTotal());

        return this.purchaseService.updateCompra(id, compra);
    }

    @MutationMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String deletePurchaseById(@Argument String id) {
        this.purchaseService.deleteById(id);
        return "Compra with id: " + id + " eliminada correctamente.";
    }
}
