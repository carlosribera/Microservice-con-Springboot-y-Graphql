package com.softand.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softand.demo.models.DetailPurchase;
import com.softand.demo.repositories.DetailPurchaseRepository;

@Service
public class DetailPurchaseService {

    @Autowired
    private DetailPurchaseRepository detailPurchaseRepository;

    public DetailPurchase getDetalleCompraById(String id) {
        return detailPurchaseRepository.findById(id).orElse(null);
    }

    public List<DetailPurchase> getAllDetalleCompras() {
        return detailPurchaseRepository.findAll();
    }

    public DetailPurchase createDetalleCompra(DetailPurchase detalleCompra) {
        return detailPurchaseRepository.save(detalleCompra);
    }

    public DetailPurchase updateDetalleCompra(String id, DetailPurchase detalleCompra) {
        if (detailPurchaseRepository.existsById(id)) {
            detalleCompra.setId(id);
            return detailPurchaseRepository.save(detalleCompra);
        }
        return null;
    }

    public void deleteById(String id) {
        detailPurchaseRepository.deleteById(id);
    }
}
