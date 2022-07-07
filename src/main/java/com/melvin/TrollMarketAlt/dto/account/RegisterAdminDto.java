package com.melvin.TrollMarketAlt.dto.account;

import com.melvin.TrollMarketAlt.validation.UniqueUsername;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterAdminDto {

    @NotBlank(message = "Username can not be null")
    @UniqueUsername
    private String username; // id / pk

    @NotBlank(message = "Password can not be null")
    private String password;
}
