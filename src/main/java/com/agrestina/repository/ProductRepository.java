package com.agrestina.repository;

import com.agrestina.domain.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository <Product, Long> {

    boolean existsByNameIgnoringCase(String name);
    @Query("SELECT p FROM Product p WHERE p.active=true")
    Page<Product> findActive(Pageable pageable);

    boolean existsByNameIgnoreCase(String name);
    @Query("SELECT p FROM Product p WHERE p.active=false")
    Page<Product> findInactive(Pageable pageable);

    boolean existsByName(String name);
    @Query("SELECT p FROM Product p")
    Page<Product> findAllProducts(Pageable pageable);

}