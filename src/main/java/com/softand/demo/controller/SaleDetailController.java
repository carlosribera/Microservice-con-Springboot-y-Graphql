package com.softand.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import com.softand.demo.models.SaleDetail;
import com.softand.demo.models.SaleDetailInput;
import com.softand.demo.service.SaleDetailService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@PreAuthorize("denyAll()")
public class SaleDetailController {

    @Autowired
    private SaleDetailService saleDetailService;

    @QueryMapping
    @PreAuthorize("hasAuthority('READ')")
    public SaleDetail getSaleDetailById(@Argument String id) throws NotFoundException {
        log.info("Query saleDetail in GraphQL Server by id {}", id);
        return saleDetailService.getSaleDetailById(id);
    }

    @QueryMapping
    @PreAuthorize("permitAll()")
    public List<SaleDetail> getAllSaleDetails() {
        return this.saleDetailService.getAllSaleDetails();
    }

    @MutationMapping
    @PreAuthorize("permitAll()")
    public SaleDetail createSaleDetail(@Argument SaleDetailInput saleDetailInput) {
        SaleDetail saleDetail = new SaleDetail();
        saleDetail.setSaleId(saleDetailInput.getSaleId());
        saleDetail.setProductId(saleDetailInput.getProductId());
        saleDetail.setQuantity(saleDetailInput.getQuantity());
        saleDetail.setProductPrice(saleDetailInput.getProductPrice());
        saleDetail.setWorkOrderId(saleDetailInput.getWorkOrderId());

        return this.saleDetailService.createSaleDetail(saleDetail);
    }

    @MutationMapping
    @PreAuthorize("hasRole('ADMIN')")
    public SaleDetail updateSaleDetail(@Argument String id, @Argument SaleDetailInput saleDetailInput) throws NotFoundException {
        SaleDetail saleDetail = new SaleDetail();
        saleDetail.setSaleId(saleDetailInput.getSaleId());
        saleDetail.setProductId(saleDetailInput.getProductId());
        saleDetail.setQuantity(saleDetailInput.getQuantity());
        saleDetail.setProductPrice(saleDetailInput.getProductPrice());
        saleDetail.setWorkOrderId(saleDetailInput.getWorkOrderId());

        return this.saleDetailService.updateSaleDetail(id, saleDetail);
    }

    @MutationMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteSaleDetailById(@Argument String id) throws NotFoundException {
        this.saleDetailService.deleteById(id);
        return "SaleDetail with id: " + id + " deleted successfully.";
    }
}

