package com.softand.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import com.softand.demo.models.Warehouse;
import com.softand.demo.models.WarehouseInput;
import com.softand.demo.service.WarehouseService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@PreAuthorize("denyAll()")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @QueryMapping
    @PreAuthorize("hasAuthority('READ')")
    public Warehouse getStorageById(@Argument String id) {
        log.info("Query storage in GraphQL Server by id {}", id);
        return warehouseService.getWarehouseById(id);
    }

    @QueryMapping
    @PreAuthorize("permitAll()")
    public List<Warehouse> getAllWarehouses() {
        return this.warehouseService.getAllWarehouses();
    }

    @MutationMapping
    @PreAuthorize("permitAll()")
    public Warehouse createWarehouse(@Argument WarehouseInput warehouseInput) {
        Warehouse storage = new Warehouse();
        storage.setName(warehouseInput.getName());
        storage.setLocation(warehouseInput.getLocation());
        storage.setPhone(warehouseInput.getPhone());

        return this.warehouseService.createWarehouse(storage);
    }

    @MutationMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Warehouse updateWarehouse(@Argument String id, @Argument WarehouseInput warehouseInput) {
        Warehouse storage = new Warehouse();
        storage.setName(warehouseInput.getName());
        storage.setLocation(warehouseInput.getLocation());
        storage.setPhone(warehouseInput.getPhone());

        return this.warehouseService.updateWarehouse(id, storage);
    }

    @MutationMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteWarehouseById(@Argument String id) {
        this.warehouseService.deleteById(id);
        return "Storage with id: " + id + " deleted successfully.";
    }
}
