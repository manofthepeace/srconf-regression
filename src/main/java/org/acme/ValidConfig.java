package org.acme;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = { ConfigValidator.class })
@Documented
public @interface ValidConfig {

    String message() default "Config is invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
