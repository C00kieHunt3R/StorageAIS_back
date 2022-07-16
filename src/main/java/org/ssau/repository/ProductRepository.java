package org.ssau.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.ssau.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


}
