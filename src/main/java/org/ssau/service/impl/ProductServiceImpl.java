package org.ssau.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ssau.model.Product;
import org.ssau.repository.ProductRepository;
import org.ssau.service.ProductService;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;



    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public Product update(Long id, Product product) {
        AtomicReference<Product> productRet = new AtomicReference<>();
        productRepository.findById(id).ifPresent(p -> {
            product.setId(p.getId());
            productRet.set(productRepository.saveAndFlush(product));
        });
        return productRet.get();
    }

    @Override
    @Transactional
    public Product create(Product product) {
        product.setId(null);
        return productRepository.saveAndFlush(product);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product getById(Long id) {
        AtomicReference<Product> productRet = new AtomicReference<>();
        productRepository.findById(id).ifPresent(productRet::set);
        return productRet.get();
    }
}
