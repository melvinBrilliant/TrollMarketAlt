package com.melvin.TrollMarketAlt.service.merchendise;

import com.melvin.TrollMarketAlt.dao.AccountRepository;
import com.melvin.TrollMarketAlt.dao.CategoryRepository;
import com.melvin.TrollMarketAlt.dao.ProductRepository;
import com.melvin.TrollMarketAlt.dto.product.ProductDto;
import com.melvin.TrollMarketAlt.dto.product.UpsertProductDto;
import com.melvin.TrollMarketAlt.model.Account;
import com.melvin.TrollMarketAlt.model.Category;
import com.melvin.TrollMarketAlt.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MerchendiseService implements IMercehndiseService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AccountRepository accountRepository;

    private final int PAGE_LIMIT = 5;

    @Override
    public Page<Product> findAllOwnedProduct(Integer page, Authentication auth) {
        String username = auth.getName();
        Pageable pageable = PageRequest.of(page-1, PAGE_LIMIT, Sort.by("id"));
        return productRepository.findAllOwnedProducts(username, pageable);
    }

    @Override
    public void isProductOwner(String sellerId, Integer productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Product not found"
                ));
        String ownerId = product.getSellerID().getId();

        if (!ownerId.equals(sellerId)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "This product is not owned by you!"
            );
        }
    }

    public ProductDto findProductById(Integer productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Product not found"
                ));
        return ProductDto.convert(product);
    }

    @Override
    public ProductDto upsertProduct(Authentication auth, Integer productId, UpsertProductDto dto) {
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category not found"
                ));

        Account seller = accountRepository.findById(auth.getName())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Seller not found"
                ));

        Product product = Product.builder()
                .id(productId)
                .productName(dto.getProductName())
                .categoryID(category)
                .description(dto.getDescription())
                .price(dto.getPrice())
                .sellerID(seller)
                .discontinued(false)
                .build();
        productRepository.save(product);

        return ProductDto.convert(product);
    }

}
