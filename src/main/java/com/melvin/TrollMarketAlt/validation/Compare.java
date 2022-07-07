package com.melvin.TrollMarketAlt.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import java.lang.annotation.*;

@Documented
@Constraint(
        validatedBy = {CompareValidator.class}
)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Compare {

    String message() default "The 2 field does not match";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    public String firstField();
    public String secondFied();

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        Compare[] value();
    }
}
