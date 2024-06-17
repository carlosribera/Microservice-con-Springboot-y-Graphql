package com.softand.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import com.softand.demo.models.Inventory;
import com.softand.demo.models.InventoryInput;
import com.softand.demo.service.InventoryService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@PreAuthorize("denyAll()")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @QueryMapping
    @PreAuthorize("hasAuthority('READ')")
    public Inventory getInventoryById(@Argument String id) {
        log.info("Query inventory in GraphQL Server by id {}", id);
        return inventoryService.getInventoryById(id);
    }

    @QueryMapping
    @PreAuthorize("permitAll()")
    public List<Inventory> getAllInventories() {
        return this.inventoryService.getAllInventories();
    }

    @MutationMapping
    @PreAuthorize("permitAll()")
    public Inventory createInventory(@Argument InventoryInput inventoryInput) {
        Inventory inventory = new Inventory();
        inventory.setWarehouseId(inventoryInput.getWarehouseId());
        inventory.setProductId(inventoryInput.getProductId());
        inventory.setQuantity(inventoryInput.getQuantity());
        inventory.setSection(inventoryInput.getSection());

        return this.inventoryService.createInventory(inventory);
    }

    @MutationMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Inventory updateInventory(@Argument String id, @Argument InventoryInput inventoryInput) {
        Inventory inventory = new Inventory();
        inventory.setWarehouseId(inventoryInput.getWarehouseId());
        inventory.setProductId(inventoryInput.getProductId());
        inventory.setQuantity(inventoryInput.getQuantity());
        inventory.setSection(inventoryInput.getSection());

        return this.inventoryService.updateInventory(id, inventory);
    }

    @MutationMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteInventoryById(@Argument String id) {
        this.inventoryService.deleteById(id);
        return "Inventory with id: " + id + " deleted successfully.";
    }
}
