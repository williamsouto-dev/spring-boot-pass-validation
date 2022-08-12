package com.api.passwordvalidation.commons;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import javax.validation.*;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ObjectValidator {

    private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private static final Validator validator = factory.getValidator();

    public static <T> void validate(T object) {
        Set<ConstraintViolation<T>> constraintValidators = validator.validate(object);

        if (!constraintValidators.isEmpty()) {
            throw new ConstraintViolationException(constraintValidators);
        }
    }
}
