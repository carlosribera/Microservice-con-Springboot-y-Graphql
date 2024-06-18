package com.softand.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softand.demo.models.Supplier;
import com.softand.demo.repositories.SupplierRepository;

@Service
public class SupplierService {

    // Lista de proveedores

    @Autowired
    private SupplierRepository supplierRepository;

    // Obtener un proveedor por ID
    public Supplier getSupplierById(String id) {
        Optional<Supplier> supplierOpt = supplierRepository.findById(id);
        return supplierOpt.orElse(null);
    }

    // Obtener todos los proveedores
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    // Crear un nuevo proveedor
    public Supplier createSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    // Actualizar un proveedor
    public Supplier updateSupplier(String id, Supplier supplier) {
        Supplier newSupplier = getSupplierById(id);
        newSupplier.setId(supplier.getId() == null ? newSupplier.getId() : supplier.getId());
        newSupplier.setName(supplier.getName() == null ? newSupplier.getName() : supplier.getName());
        newSupplier.setNit(supplier.getNit() == null ? newSupplier.getNit() : supplier.getNit());
        newSupplier.setPhone(supplier.getPhone() == null ? newSupplier.getPhone() : supplier.getPhone());
        return supplierRepository.save(newSupplier);
    }

    // Eliminar un proveedor por ID
    public void deleteById(String id) {
        supplierRepository.deleteById(id);
    }
}

