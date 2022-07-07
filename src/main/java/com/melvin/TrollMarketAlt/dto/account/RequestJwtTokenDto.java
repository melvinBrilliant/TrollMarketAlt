package com.melvin.TrollMarketAlt.dto.account;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RequestJwtTokenDto {

    @NotBlank(message = "Username can not be blank")
    private final String username;

    @NotBlank(message = "Password can not be blank")
    private final String password;
}
