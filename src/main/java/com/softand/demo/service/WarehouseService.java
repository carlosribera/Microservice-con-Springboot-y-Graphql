package com.softand.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.softand.demo.common.exception.WarehouseNotFoundException;
import com.softand.demo.models.Warehouse;
import com.softand.demo.repositories.WarehouseRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class WarehouseService {

    @Autowired
    WarehouseRepository warehouseRepository;

    public Warehouse getWarehouseById(String id) {
        return warehouseRepository.findById(id)
                .orElseThrow(() -> new WarehouseNotFoundException("Warehouse not found with ID: " + id));
    }

    public List<Warehouse> getAllWarehouses() {
        List<Warehouse> warehouseList = warehouseRepository.findAll();
        if (CollectionUtils.isEmpty(warehouseList)) {
            log.info("Warehouse not found");
            return null;
        }
        return warehouseList;
    }

    public Warehouse createWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    public Warehouse updateWarehouse(String id, Warehouse warehouseEntity) {
        Warehouse warehouse = getWarehouseById(id);
        warehouse.setName(warehouseEntity.getName());
        warehouse.setLocation(warehouseEntity.getLocation());
        warehouse.setPhone(warehouseEntity.getPhone());

        return warehouseRepository.save(warehouse);
    }

    public String deleteById(String id) {
        Warehouse warehouse = getWarehouseById(id);
        warehouseRepository.deleteById(warehouse.getId());
        return "Warehouse by id: " + id + " deleted.";
    }
}