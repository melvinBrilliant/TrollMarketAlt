package com.melvin.TrollMarketAlt.controller.rest;

import com.melvin.TrollMarketAlt.dto.RestResponse;
import com.melvin.TrollMarketAlt.dto.product.ProductDto;
import com.melvin.TrollMarketAlt.model.Product;
import com.melvin.TrollMarketAlt.service.shop.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/shop")
public class ShopController {

    @Autowired
    private ShopService service;

    @GetMapping
    public ResponseEntity<RestResponse<List<ProductDto>>> showProductsInShop (
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "") String productName,
            @RequestParam(defaultValue = "") String categoryName,
            @RequestParam(defaultValue = "") String description
    ) {
        Page<Product> products = service.findAllProducts(page, productName, categoryName, description);
        List<ProductDto> productDtoList = ProductDto.toList(products.getContent());
        int totalPages = products.getTotalPages();
        String message = String.format("Showing products from page %s out of %s pages",
                page, totalPages);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new RestResponse<>(
                        productDtoList,
                        message,
                        "200"
                ));
    }
}
