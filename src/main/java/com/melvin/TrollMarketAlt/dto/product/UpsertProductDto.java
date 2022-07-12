package com.melvin.TrollMarketAlt.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpsertProductDto {

    private Integer productId;

    @NotBlank(message = "Product name can not be blank")
    private String productName;

    @NotNull(message = "Please fill the category for product")
    private Integer categoryId;

    private String description;

    @NotNull(message = "Product should have a price")
    private BigDecimal price;
}
