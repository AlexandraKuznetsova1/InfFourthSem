/*
package com.itis.validation.annotations;

import com.taxi.validation.validators.EmailNotTakenValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailNotTakenValidator.class)
public @interface EmailNotTaken {
    String message() default "Email's been already taken";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
*/
