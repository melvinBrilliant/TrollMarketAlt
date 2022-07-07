package com.melvin.TrollMarketAlt.dto.account;

import com.melvin.TrollMarketAlt.model.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.NumberFormat;
import java.util.Locale;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDto {
    private String username; // pk / id
    private String name;
    private String address;
    private String role;
    private String balance;

    public static AccountDto convert(Account account) {
        return AccountDto.builder()
                .username(account.getId())
                .name(account.getFullName())
                .address(account.getAddress())
                .role(account.getRole())
                .balance(NumberFormat.getCurrencyInstance(
                        new Locale("id", "ID")).format(account.getBalance()))
                .build();
    }

    public static AccountDto convertAdmin(Account account) {
        return AccountDto.builder()
                .username(account.getId())
                .role(account.getRole())
                .build();
    }
}
