package com.softand.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.softand.demo.common.exception.SaleNotFoundException;
import com.softand.demo.models.Sale;    
import com.softand.demo.repositories.SaleRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SaleService {

    @Autowired
    SaleRepository saleRepository;

    public Sale getSaleById(String id) {
        return saleRepository.findById(id)
                .orElseThrow(() -> new SaleNotFoundException("Sale not found with ID: " + id));
    }

    public List<Sale> getAllSales() {
        List<Sale> saleList = saleRepository.findAll();
        if (CollectionUtils.isEmpty(saleList)) {
            log.info("Sale not found");
            return null;
        }
        return saleList;
    }

    public Sale createSale(Sale sale) {
        return saleRepository.save(sale);
    }

    public Sale updateSale(String id, Sale saleEntity) {
        Sale sale = getSaleById(id);

        sale.setClientId(saleEntity.getClientId());
        sale.setTotalPrice(saleEntity.getTotalPrice());

        return saleRepository.save(sale);
    }

    public String deleteById(String id) {
        Sale sale = getSaleById(id);
        saleRepository.deleteById(sale.getId());
        return "Sale by id: " + id + " deleted.";
    }
}