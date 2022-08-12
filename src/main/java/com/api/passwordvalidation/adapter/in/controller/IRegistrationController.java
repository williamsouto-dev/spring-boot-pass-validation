package com.api.passwordvalidation.adapter.in.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface IRegistrationController {

    @Operation(summary = "Validates if the password is in the expected pattern")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Password has been successfully validated",
                    content = { @Content(mediaType = "application/json",schema = @Schema(implementation = Boolean.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid password",
                    content = @Content)
    })
    Boolean validatePassword(@Parameter(description = "Password that will be validated", required = true) String password);
}
