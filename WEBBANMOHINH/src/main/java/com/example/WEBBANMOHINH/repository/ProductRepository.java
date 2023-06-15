package com.example.WEBBANMOHINH.repository;

import com.example.WEBBANMOHINH.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE p.name like  %:key%")
    Page<Product> searchProducts(@Param("key") String key, Pageable pageable);
}
