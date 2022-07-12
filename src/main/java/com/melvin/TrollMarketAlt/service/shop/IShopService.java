package com.melvin.TrollMarketAlt.service.shop;

import com.melvin.TrollMarketAlt.dto.product.ProductDetailDto;
import com.melvin.TrollMarketAlt.model.Product;
import org.springframework.data.domain.Page;

public interface IShopService {
    Page<Product> findAllProducts(Integer page, String productName, String categoryName, String description);
    ProductDetailDto showProductDetail(Integer productId);
}
