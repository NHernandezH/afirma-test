package com.afirma.test.bookings.common.validators;

import com.afirma.test.bookings.common.constants.Date;
import com.afirma.test.bookings.utils.DateUtils;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

public class DateValidator implements ConstraintValidator<DateValidator.DateValidation, String> {


    @Override
    public void initialize(DateValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return DateUtils.stringToLocalDate(value)!=null;
    }

    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
    @Retention(RUNTIME)
    @Documented
    @Constraint(validatedBy = DateValidator.class)
    public @interface DateValidation{

        String pattern() default Date.PATTERN;
        String message() default "must be any of enum {enumClass}";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }
}
