package com.melvin.TrollMarketAlt.controller.rest;

import com.melvin.TrollMarketAlt.dto.RestResponse;
import com.melvin.TrollMarketAlt.dto.product.ProductDto;
import com.melvin.TrollMarketAlt.dto.product.UpsertProductDto;
import com.melvin.TrollMarketAlt.model.Product;
import com.melvin.TrollMarketAlt.service.merchendise.MerchendiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/merchendise")
public class MerchendiseRestController {

    @Autowired
    private MerchendiseService service;

    @GetMapping
    public ResponseEntity<RestResponse<List<ProductDto>>> findAllOwnedProducts(
            Authentication auth,
            @RequestParam(defaultValue = "1") Integer page
    ) {
        Page<Product> fetchedProducts = service.findAllOwnedProduct(page, auth);
        int totalPage = fetchedProducts.getTotalPages();
        List<ProductDto> ownedProducts = ProductDto.toList(fetchedProducts.getContent());
        String message = String.format("Showing %s owned product(s) from page %s out of %s pages",
                ownedProducts.size(), page, totalPage);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new RestResponse<>(
                        ownedProducts,
                        message,
                        "201"
                ));
    }

    @PostMapping("/upsert")
    public ResponseEntity<RestResponse<ProductDto>> upsertProduct(
            Authentication auth,
            @RequestParam(required = false) Integer productId,
            @Valid @RequestBody UpsertProductDto productDto
            ) {
        String message;
        if (productDto.getProductId() != null) {
            ProductDto product = service.findProductById(productId);
            String sellerId = product.getSellerId();
            if (!auth.getName().equals(sellerId)) {
                throw new ResponseStatusException(
                        HttpStatus.FORBIDDEN,
                        "You are not the owner of this product"
                );
            }
            message = String.format("Product %s has been successfully updated",
                    productDto.getProductName());
        } else {
            message = String.format("New product %s has been created successfully",
                    productDto.getProductName());
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new RestResponse<>(
                        service.upsertProduct(auth, productId, productDto),
                        message,
                        "201"
                ));
    }
}
