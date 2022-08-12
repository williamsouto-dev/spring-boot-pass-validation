package com.api.passwordvalidation.domain;

import com.api.passwordvalidation.domain.validation.Password;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.validation.ConstraintViolationException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class PasswordTest {

    @Test
    public void field_null() {
        Throwable exception = assertThrows(ConstraintViolationException.class, () -> Password.of(null).check());
        assertEquals("password: não deve estar em branco", exception.getMessage());;
    }
}
