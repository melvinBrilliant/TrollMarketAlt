package com.melvin.TrollMarketAlt.dto.product;

import com.melvin.TrollMarketAlt.model.Product;
import lombok.Data;
import lombok.ToString;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Data
@ToString
public class ProductDto {
    private final Integer productId;
    private final String productName;
    private final Integer categoryId;
    private final String description;
    private final String price;
    private final String sellerId;
    private final String isDiscontinued;

    public static List<ProductDto> toList(List<Product> products) {
        List<ProductDto> result = new ArrayList<>();
        products.stream()
                .map(ProductDto::convert)
                .forEach(result::add);
        return result;
    }

    public static ProductDto convert(Product product) {
        return new ProductDto(
                product.getId(),
                product.getProductName(),
                product.getCategoryID().getId(),
                product.getDescription(),
                NumberFormat.getCurrencyInstance(
                        new Locale("id", "ID")).format(product.getPrice()),
                product.getSellerID().getId(),
                product.getDiscontinued() ? "Yes" : "No"
        );
    }
}
