package com.melvin.TrollMarketAlt.dto.account;

import com.melvin.TrollMarketAlt.validation.Compare;
import com.melvin.TrollMarketAlt.validation.UniqueUsername;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Compare(firstField = "password", secondFied = "confirmPassword", message = "Password  does not match")
public class RegisterSellerBuyerDto {

    @NotBlank(message = "Please enter your desired username")
    @Size(max = 20, message = "Username can not be more than 20 characters")
    @UniqueUsername
    private String username;

    @NotBlank(message = "Password can not be blank")
    @Size(max = 20, message = "Password can not be more than 20 characters")
    private String password;

    @NotBlank(message = "Please confirm your password")
    private String confirmPassword;

    @NotBlank(message = "Please enter your name")
    private String name;

    @NotBlank(message = "Please enter your address")
    private String address;

    @NotBlank(message = "Please choose between seller or buyer")
    private String role;
}
