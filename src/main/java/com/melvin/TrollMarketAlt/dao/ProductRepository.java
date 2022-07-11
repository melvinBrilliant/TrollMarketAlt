package com.melvin.TrollMarketAlt.dao;

import com.melvin.TrollMarketAlt.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = """
            SELECT p
            FROM Product p
            INNER JOIN p.categoryID AS c
            WHERE (
                p.productName LIKE %:productName% OR
                c.categoryName LIKE %:categoryName% OR
                p.description LIKE %:description%
            ) AND p.discontinued = false
            """)
    Page<Product> findProductsInShop(String productName,
                                     String categoryName,
                                     String description,
                                     Pageable pageable);
}