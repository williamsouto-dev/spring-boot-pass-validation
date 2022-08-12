package com.api.passwordvalidation.adapter.in.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AccountRequest {

    private final String username;
    private final String email;
    private final String password;
}
