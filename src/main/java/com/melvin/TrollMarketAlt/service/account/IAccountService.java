package com.melvin.TrollMarketAlt.service.account;

import com.melvin.TrollMarketAlt.dto.account.AccountDto;
import com.melvin.TrollMarketAlt.dto.account.RegisterAdminDto;
import com.melvin.TrollMarketAlt.dto.account.RegisterSellerBuyerDto;
import com.melvin.TrollMarketAlt.model.Account;

public interface IAccountService {
    AccountDto registerAdmin(RegisterAdminDto dto);
    AccountDto registerSellerBuyer(RegisterSellerBuyerDto dto);
}
