package com.melvin.TrollMarketAlt.service.account;

import com.melvin.TrollMarketAlt.dto.account.AccountDto;
import com.melvin.TrollMarketAlt.dto.account.RegisterAdminDto;
import com.melvin.TrollMarketAlt.dto.account.RegisterSellerBuyerDto;

public interface IAccountService {
    AccountDto registerAdmin(RegisterAdminDto dto);
    AccountDto registerSellerBuyer(RegisterSellerBuyerDto dto);
    AccountDto viewMyProfile(String username);
}
