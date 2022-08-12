package com.api.passwordvalidation.domain.validation;

import com.api.passwordvalidation.commons.ObjectValidator;
import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
public class Password {

    @NotBlank
    @Pattern(regexp = "^(?!.*[\\s])(?=.*[A-Z])(?=.*[!@#$%^&*()\\-+])(?=.*[0-9])(?=.*[a-z])(?!.*(.{1}).*\\1.*).{9,}$")
    private String password;

    public static Password of(String pass) {
        return new Password(pass);
    }

    public void check() {
        ObjectValidator.validate(this);
    }
}
