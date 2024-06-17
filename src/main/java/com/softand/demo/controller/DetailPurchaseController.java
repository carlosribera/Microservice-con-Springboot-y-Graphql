package com.softand.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import com.softand.demo.models.DetailPurchase;
import com.softand.demo.models.DetailPurchaseInput;
import com.softand.demo.service.DetailPurchaseService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@PreAuthorize("denyAll()")
public class DetailPurchaseController {

    @Autowired
    private DetailPurchaseService detailPurchaseService;

    @QueryMapping
    @PreAuthorize("hasAuthority('READ')")
    public DetailPurchase getDetailPurchaseById(@Argument String id) {
        log.info("Query detalleCompra in GraphQL Server by id {}", id);
        return detailPurchaseService.getDetalleCompraById(id);
    }

    @QueryMapping
    @PreAuthorize("permitAll()")
    public List<DetailPurchase> getAllDetailPurchases() {
        return this.detailPurchaseService.getAllDetalleCompras();
    }

    @MutationMapping
    @PreAuthorize("permitAll()")
    public DetailPurchase addDetailPurchase(@Argument DetailPurchaseInput detailPurchaseInput) {
        DetailPurchase detalleCompra = new DetailPurchase();
        detalleCompra.setIdProducto(detailPurchaseInput.getIdProducto());
        detalleCompra.setCantidad(detailPurchaseInput.getCantidad());
        detalleCompra.setPrecioProducto(detailPurchaseInput.getPrecioProducto());

        return this.detailPurchaseService.createDetalleCompra(detalleCompra);
    }

    @MutationMapping
    @PreAuthorize("hasRole('ADMIN')")
    public DetailPurchase updateDetailPurchase(@Argument String id, @Argument DetailPurchaseInput detailPurchaseInput) {
        DetailPurchase detalleCompra = new DetailPurchase();
        detalleCompra.setId(id); // Asegúrate de establecer el ID correcto
        detalleCompra.setIdProducto(detailPurchaseInput.getIdProducto());
        detalleCompra.setCantidad(detailPurchaseInput.getCantidad());
        detalleCompra.setPrecioProducto(detailPurchaseInput.getPrecioProducto());

        return this.detailPurchaseService.updateDetalleCompra(id, detalleCompra);
    }

    @MutationMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteDetailPurchaseById(@Argument String id) {
        this.detailPurchaseService.deleteById(id);
        return "DetalleCompra with id: " + id + " eliminado correctamente.";
    }
}
