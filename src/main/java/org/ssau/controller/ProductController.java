package org.ssau.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.ssau.model.Product;
import org.ssau.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@CrossOrigin
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_MERCHANDISER') or hasRole('ROLE_ADMIN')")
    public List<Product> getAll() {

        return productService.getAll();
    }

    @GetMapping(path = "/{id}")
    @PreAuthorize("hasRole('ROLE_MERCHANDISER') or hasRole('ROLE_ADMIN')")
    public Product getById(@PathVariable("id") Long id) {
        return productService.getById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_MERCHANDISER') or hasRole('ROLE_ADMIN')")
    public Product create(@RequestBody Product product) {
       return productService.create(product);
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasRole('ROLE_MERCHANDISER') or hasRole('ROLE_ADMIN')")
    public Product update(@PathVariable("id") Long id,
                          @RequestBody Product product) {
        return productService.update(id, product);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasRole('ROLE_MERCHANDISER') or hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }
}
