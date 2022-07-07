package com.melvin.TrollMarketAlt.dao;

import com.melvin.TrollMarketAlt.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}