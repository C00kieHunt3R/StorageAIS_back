package org.ssau.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.ssau.model.ConsumerOperation;
import org.ssau.model.Product;
import org.ssau.service.ConsumerOperationService;
import org.ssau.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/consumer-operation")
@RequiredArgsConstructor
@CrossOrigin
public class ConsumerOperationController {

    @Autowired
    private ConsumerOperationService consumerOperationService;
    @Autowired
    private ProductService productService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_MERCHANDISER') or hasRole('ROLE_ADMIN')")
    public List<ConsumerOperation> getAll() {
        return consumerOperationService.getAll();
    }

    @GetMapping(path = "/{id}")
    @PreAuthorize("hasRole('ROLE_MERCHANDISER') or hasRole('ROLE_ADMIN')")
    public ConsumerOperation getById(@PathVariable("id") Long id) {
        return consumerOperationService.getById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_MERCHANDISER') or hasRole('ROLE_ADMIN')")
    public ConsumerOperation create(@RequestBody ConsumerOperation consumerOperation) {
        Product product = productService.getById(consumerOperation.getProduct().getId());
        product.setTotalValue(product.getTotalValue() - consumerOperation.getProduct().getTotalValue());
        productService.update(product.getId(), product);
        return consumerOperationService.create(consumerOperation);
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasRole('ROLE_MERCHANDISER') or hasRole('ROLE_ADMIN')")
    public ConsumerOperation update(@PathVariable("id") Long id, @RequestBody ConsumerOperation consumerOperation) {
        Product product = productService.getById(consumerOperation.getProduct().getId());
        if (product.getTotalValue() != consumerOperation.getProduct().getTotalValue()) {
            product.setTotalValue(product.getTotalValue() - consumerOperation.getProduct().getTotalValue());
        }
        return consumerOperationService.update(id, consumerOperation);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasRole('ROLE_MERCHANDISER') or hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable("id") Long id) {
        consumerOperationService.delete(id);
    }
}
