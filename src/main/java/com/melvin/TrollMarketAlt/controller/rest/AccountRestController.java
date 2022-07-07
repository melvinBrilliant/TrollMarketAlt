package com.melvin.TrollMarketAlt.controller.rest;

import com.melvin.TrollMarketAlt.dto.RestResponse;
import com.melvin.TrollMarketAlt.dto.account.AccountDto;
import com.melvin.TrollMarketAlt.dto.account.RegisterAdminDto;
import com.melvin.TrollMarketAlt.dto.account.RegisterSellerBuyerDto;
import com.melvin.TrollMarketAlt.model.Account;
import com.melvin.TrollMarketAlt.service.MyUserDetailsService;
import com.melvin.TrollMarketAlt.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Locale;

@RestController
@RequestMapping("/api/account")
public class AccountRestController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/auth")
    public Authentication auth(Authentication authentication) {
        return authentication;
    }

    @PostMapping("/register")
    public ResponseEntity<RestResponse<AccountDto>> registerSellerBuyer(
            @Valid @RequestBody RegisterSellerBuyerDto dto,
            BindingResult bindingResult
    ) {
        AccountDto outputDto = accountService.registerSellerBuyer(dto);
        String message = String.format("New %s account has been created",
                outputDto.getRole().toLowerCase());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new RestResponse<>(
                        accountService.registerSellerBuyer(dto),
                        message,
                        "201"
                ));
    }

    @PostMapping("/register/admnin")
    public ResponseEntity<RestResponse<AccountDto>> registerAdminDto(
            @Valid @RequestBody RegisterAdminDto dto
            ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new RestResponse<>(
                        accountService.registerAdmin(dto),
                        "New Admin account has been created",
                        "201"
                ));
    }
}
