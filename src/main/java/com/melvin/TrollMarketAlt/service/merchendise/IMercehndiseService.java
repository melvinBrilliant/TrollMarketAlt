package com.melvin.TrollMarketAlt.service.merchendise;

import com.melvin.TrollMarketAlt.dto.product.ProductDto;
import com.melvin.TrollMarketAlt.dto.product.UpsertProductDto;
import com.melvin.TrollMarketAlt.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;

public interface IMercehndiseService {
    Page<Product> findAllOwnedProduct(Integer page, Authentication auth);
    void isProductOwner(String sellerId, Integer productId);
    ProductDto findProductById(Integer productId);
    ProductDto upsertProduct(Authentication auth, Integer productId, UpsertProductDto dto);
}
