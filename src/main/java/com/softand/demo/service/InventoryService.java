package com.softand.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.softand.demo.common.exception.InventoryNotFoundException;
import com.softand.demo.models.Inventory;
import com.softand.demo.repositories.InventoryRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    public Inventory getInventoryById(String id) {
        return inventoryRepository.findById(id)
                .orElseThrow(() -> new InventoryNotFoundException("Inventory not found with ID: " + id));
    }

    public List<Inventory> getAllInventories() {
        List<Inventory> inventoryList = inventoryRepository.findAll();
        if (CollectionUtils.isEmpty(inventoryList)) {
            log.info("Inventory not found");
            return null;
        }
        return inventoryList;
    }

    public Inventory createInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public Inventory updateInventory(String id, Inventory inventoryEntity) {
        Inventory inventory = getInventoryById(id);
        inventory.setWarehouseId(inventoryEntity.getWarehouseId());
        inventory.setProductId(inventoryEntity.getProductId());
        inventory.setQuantity(inventoryEntity.getQuantity());
        inventory.setSection(inventoryEntity.getSection());

        return inventoryRepository.save(inventory);
    }

    public String deleteById(String id) {
        Inventory inventory = getInventoryById(id);
        inventoryRepository.deleteById(inventory.getId());
        return "Inventory by id: " + id + " deleted.";
    }
}
