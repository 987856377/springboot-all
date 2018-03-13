package com.example.demo.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by daier on 2018/2/26.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Constraint(validatedBy = SelfValidatorInfo.class)
public @interface SelfValildator {
    String value();
    String message() default "not exit";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
