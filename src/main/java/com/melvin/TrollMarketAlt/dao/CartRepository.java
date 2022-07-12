package com.melvin.TrollMarketAlt.dao;

import com.melvin.TrollMarketAlt.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}