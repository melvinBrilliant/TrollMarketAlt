package com.melvin.TrollMarketAlt.dto.product;

import com.melvin.TrollMarketAlt.model.Product;
import lombok.Data;

import java.text.NumberFormat;
import java.util.Locale;

@Data
public class ProductDetailDto {
    private final Integer productId;
    private final String productName;
    private final String categoryName;
    private final String description;
    private final String price;
    private final String sellerName;

    public static ProductDetailDto convert(Product product) {
        return new ProductDetailDto(
                product.getId(),
                product.getProductName(),
                product.getCategoryID().getCategoryName(),
                product.getDescription(),
                NumberFormat.getCurrencyInstance(new Locale("id", "ID")).format(product.getPrice()),
                product.getSellerID().getFullName()
        );
    }
}
