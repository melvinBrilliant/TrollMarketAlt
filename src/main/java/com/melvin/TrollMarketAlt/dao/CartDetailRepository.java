package com.melvin.TrollMarketAlt.dao;

import com.melvin.TrollMarketAlt.model.CartDetail;
import com.melvin.TrollMarketAlt.model.CartDetailId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartDetailRepository extends JpaRepository<CartDetail, CartDetailId> {
}