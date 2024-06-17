package com.softand.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import com.softand.demo.models.SupplierInput;
import com.softand.demo.models.Supplier;
import com.softand.demo.service.SupplierService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@PreAuthorize("denyAll()")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @QueryMapping
    @PreAuthorize("hasAuthority('READ')")
    public Supplier getSupplierById(@Argument String id) {
        log.info("Query supplier in GraphQL Server by id {}", id);
        return supplierService.getSupplierById(id);
    }

    @QueryMapping
    @PreAuthorize("permitAll()")
    public List<Supplier> getAllSuppliers() {
        return this.supplierService.getAllSuppliers();
    }

    @MutationMapping
    @PreAuthorize("permitAll()")
    public Supplier createSupplier(@Argument SupplierInput supplierInput) {
        Supplier supplier = new Supplier();
        supplier.setName(supplierInput.getName());
        supplier.setNit(supplierInput.getNit());
        supplier.setTelefono(supplierInput.getTelefono());

        return this.supplierService.createSupplier(supplier);
    }

    @MutationMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Supplier updateSupplier(@Argument String id, @Argument SupplierInput supplierInput) {
        Supplier supplier = new Supplier();
        supplier.setId(id); // Asegúrate de establecer el ID correcto
        supplier.setName(supplierInput.getName());
        supplier.setNit(supplierInput.getNit());
        supplier.setTelefono(supplierInput.getTelefono());

        return this.supplierService.updateSupplier(id, supplier);
    }

    @MutationMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteSupplierById(@Argument String id) {
        this.supplierService.deleteById(id);
        return "Supplier with id: " + id + " eliminado correctamente.";
    }
}
