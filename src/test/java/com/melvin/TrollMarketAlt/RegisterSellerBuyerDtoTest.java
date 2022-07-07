package com.melvin.TrollMarketAlt;

import com.melvin.TrollMarketAlt.dto.account.RegisterSellerBuyerDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RegisterSellerBuyerDtoTest extends AbstractValidatorTest{

    private final RegisterSellerBuyerDto register = new RegisterSellerBuyerDto(
            "buyer1",
            "buyer",
            "buyer",
            "Buyer-san",
            "Next to my neigbour",
            "BUYER"
    );
    private final RegisterSellerBuyerDto blankRegister = new RegisterSellerBuyerDto();
    private

    @Test
    void testRegisterDtoConstraintsAllValid() {
        Assertions.assertEquals(0, validate(register));
    }

    @Test
    void testRegisterSellerBuyerDtoNotBlankInvalid() {
        Assertions.assertEquals(6, validate(blankRegister));
    }

    @Test
    void testRegisterSellerBuyerDtoSizeInvalid() {
        register.setUsername("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        register.setPassword("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        Assertions.assertEquals(2, validate(register));
    }

    @Test
    void testRegisterSellerBuyerDtoCompareInvalid() {
        register.setPassword("notBuyer");
        Assertions.assertEquals(1, validate(register));
    }
}
