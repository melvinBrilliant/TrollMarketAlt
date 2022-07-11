package com.melvin.TrollMarketAlt.service.shop;

import com.melvin.TrollMarketAlt.dao.ProductRepository;
import com.melvin.TrollMarketAlt.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ShopService {
    @Autowired
    private ProductRepository productRepository;

    private final int PAGE_LIMIT = 5;

    public Page<Product> findAllProducts(Integer page,
                                            String productName,
                                            String categoryName,
                                            String description) {
        Pageable pageable = PageRequest.of(page-1, PAGE_LIMIT, Sort.by("id"));
        return productRepository.findProductsInShop(
                productName,
                categoryName,
                description,
                pageable
        );
    }
}
