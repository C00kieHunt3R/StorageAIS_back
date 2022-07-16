package org.ssau.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.ssau.model.Product;
import org.ssau.model.SupplierOperation;
import org.ssau.service.ProductService;
import org.ssau.service.SupplierOperationService;

import java.util.List;

@RestController
@RequestMapping("/supplier-operation")
@RequiredArgsConstructor
@CrossOrigin
public class SupplierOperationController {

    @Autowired
    private SupplierOperationService supplierOperationService;

    @Autowired
    private ProductService productService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_MERCHANDISER') or hasRole('ROLE_ADMIN')")
    public List<SupplierOperation> getAll() {
        return supplierOperationService.getAll();
    }

    @GetMapping(path = "/{id}")
    @PreAuthorize("hasRole('ROLE_MERCHANDISER') or hasRole('ROLE_ADMIN')")
    public SupplierOperation getById(@PathVariable("id") Long id) {
        return supplierOperationService.getById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_MERCHANDISER') or hasRole('ROLE_ADMIN')")
    public SupplierOperation create(@RequestBody SupplierOperation supplierOperation) {
        Product product = productService.getById(supplierOperation.getProduct().getId());
        product.setTotalValue(product.getTotalValue() + supplierOperation.getProduct().getTotalValue());
        productService.update(product.getId(), product);
        return supplierOperationService.create(supplierOperation);
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasRole('ROLE_MERCHANDISER') or hasRole('ROLE_ADMIN')")
    public SupplierOperation update(@PathVariable("id") Long id,
                                    @RequestBody SupplierOperation supplierOperation) {
        Product product = productService.getById(supplierOperation.getProduct().getId());
        if (product.getTotalValue() != supplierOperation.getProduct().getTotalValue()) {
            product.setTotalValue(product.getTotalValue() + supplierOperation.getProduct().getTotalValue());
        }
        return supplierOperationService.update(id, supplierOperation);

    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasRole('ROLE_MERCHANDISER') or hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable Long id) {
        supplierOperationService.delete(id);
    }
}
