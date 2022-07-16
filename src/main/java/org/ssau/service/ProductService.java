package org.ssau.service;

import org.ssau.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    Product update(Long id, Product product);
    Product create(Product product);
    void delete(Long id);
    Product getById(Long id);
}
