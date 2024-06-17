package com.softand.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import com.softand.demo.models.Sale;
import com.softand.demo.models.SaleInput;
import com.softand.demo.service.SaleService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@PreAuthorize("denyAll()")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @QueryMapping
    @PreAuthorize("hasAuthority('READ')")
    public Sale getSaleById(@Argument String id) {
        log.info("Query sale in GraphQL Server by id {}", id);
        return saleService.getSaleById(id);
    }

    @QueryMapping
    @PreAuthorize("permitAll()")
    public List<Sale> getAllSales() {
        return this.saleService.getAllSales();
    }

    @MutationMapping
    @PreAuthorize("permitAll()")
    public Sale createSale(@Argument SaleInput saleInput) {
        Sale sale = new Sale();
        sale.setClientId(saleInput.getClientId());
        sale.setTotalPrice(saleInput.getTotalPrice());

        return this.saleService.createSale(sale);
    }

    @MutationMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Sale updateSale(@Argument String id, @Argument SaleInput saleInput) {
        Sale sale = new Sale();
        sale.setClientId(saleInput.getClientId());
        sale.setTotalPrice(saleInput.getTotalPrice());

        return this.saleService.updateSale(id, sale);
    }

    @MutationMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteSaleById(@Argument String id) {
        this.saleService.deleteById(id);
        return "Sale with id: " + id + " deleted successfully.";
    }
}
