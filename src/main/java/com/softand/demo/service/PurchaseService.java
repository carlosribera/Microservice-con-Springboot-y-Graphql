package com.softand.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softand.demo.models.Purchase;
import com.softand.demo.repositories.PurchaseRepository;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    // Obtener una compra por ID
    public Purchase getCompraById(String id) {
        Purchase compra = purchaseRepository.findById(id).orElse(null);
        return compra;
    }

    // Obtener todas las compras
    public List<Purchase> getAllCompras() {
        return purchaseRepository.findAll();
    }

    // Crear una nueva compra
    public Purchase createPurchase(Purchase compra) {
        Purchase newPurchase = purchaseRepository.save(compra);
        return newPurchase;
    }

    // Actualizar una compra
    public Purchase updateCompra(String id, Purchase compra) {
        Purchase newPurchase = getCompraById(id);
        newPurchase.setIdSupplier(compra.getIdSupplier() == null?newPurchase.getIdSupplier():compra.getIdSupplier());
        newPurchase.setPrecioTotal(compra.getPrecioTotal());
        return purchaseRepository.save(newPurchase);
    }

    // Eliminar una compra por ID
    public void deleteById(String id) {
        purchaseRepository.deleteById(id);
    }
}
