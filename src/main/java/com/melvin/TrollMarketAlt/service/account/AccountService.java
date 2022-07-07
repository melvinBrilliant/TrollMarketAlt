package com.melvin.TrollMarketAlt.service.account;

import com.melvin.TrollMarketAlt.config.RestSecurityConfig;
import com.melvin.TrollMarketAlt.dao.AccountRepository;
import com.melvin.TrollMarketAlt.dto.account.AccountDto;
import com.melvin.TrollMarketAlt.dto.account.RegisterAdminDto;
import com.melvin.TrollMarketAlt.dto.account.RegisterSellerBuyerDto;
import com.melvin.TrollMarketAlt.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountService implements IAccountService{

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AccountDto registerAdmin(RegisterAdminDto dto) {
        PasswordEncoder passwordEncoder = RestSecurityConfig.passwordEncoder();
        String hashPassword = passwordEncoder.encode(dto.getPassword());
        Account account = Account.builder()
                .id(dto.getUsername())
                .password(hashPassword)
                .role("ADMIN")
                .build();
        accountRepository.save(account);
        return AccountDto.convertAdmin(account);
    }

    @Override
    public AccountDto registerSellerBuyer(RegisterSellerBuyerDto dto) {
        PasswordEncoder passwordEncoder = RestSecurityConfig.passwordEncoder();
        String hashPassword = passwordEncoder.encode(dto.getPassword());
        Account account = Account.builder()
                .id(dto.getUsername())
                .password(hashPassword)
                .fullName(dto.getName())
                .address(dto.getAddress())
                .balance(BigDecimal.ZERO)
                .role(dto.getRole())
                .build();
        accountRepository.save(account);
        return AccountDto.convert(account);
    }
}
