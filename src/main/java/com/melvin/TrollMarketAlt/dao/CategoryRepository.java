package com.melvin.TrollMarketAlt.dao;

import com.melvin.TrollMarketAlt.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}