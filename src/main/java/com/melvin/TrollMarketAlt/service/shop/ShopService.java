package com.melvin.TrollMarketAlt.service.shop;

import com.melvin.TrollMarketAlt.dao.ProductRepository;
import com.melvin.TrollMarketAlt.dto.product.ProductDetailDto;
import com.melvin.TrollMarketAlt.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ShopService implements IShopService{
    @Autowired
    private ProductRepository productRepository;

    private final int PAGE_LIMIT = 5;

    @Override
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

    @Override
    public ProductDetailDto showProductDetail(Integer productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Product not found"
                ));
        return ProductDetailDto.convert(product);
    }

}
