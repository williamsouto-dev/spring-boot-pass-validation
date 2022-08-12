package com.api.passwordvalidation.adapter.in.controller;

import com.api.passwordvalidation.application.in.IRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/registration")
public class RegistrationController implements IRegistrationController {

    private final IRegistrationService service;

    @PostMapping("/validate")
    public Boolean validatePassword(@RequestBody String password) {
        return service.validatePassword(password);
    }
}
