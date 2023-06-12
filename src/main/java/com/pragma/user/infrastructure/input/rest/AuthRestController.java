package com.pragma.user.infrastructure.input.rest;

import com.pragma.user.application.dto.request.LoginRequest;
import com.pragma.user.application.dto.response.LoginResponse;
import com.pragma.user.application.handler.IUserHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthRestController {

    private final IUserHandler userHandler;

    @Operation(summary = "User Authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. User Authenticated successfully", content = @Content),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST. JSON request is invalid", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. User not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED. User is not authorized", content = @Content)
    })
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(userHandler.authenticate(loginRequest));
    }
}
