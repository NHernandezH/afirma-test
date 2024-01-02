package com.afirma.test.bookings.common.validators;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Arrays;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

public class EnumValidator implements ConstraintValidator<EnumValidator.EnumValidation, String> {

    private Class<? extends Enum<?>> enumClas;

    @Override
    public void initialize(EnumValidation constraintAnnotation) {
        enumClas = constraintAnnotation.enumClass();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
            return Arrays.stream(enumClas.getDeclaredFields())
                    .anyMatch(field -> field.getName().equals(value.toUpperCase()));
    }

    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
    @Retention(RUNTIME)
    @Documented
    @Constraint(validatedBy = EnumValidator.class)
    public @interface EnumValidation{

        Class<? extends Enum<?>> enumClass();
        String message() default "must be any of enum {enumClass}";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }
}


