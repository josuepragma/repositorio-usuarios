package com.pragma.user.infrastructure.input.rest;

import com.pragma.user.application.dto.LoginRequest;
import com.pragma.user.application.dto.LoginResponse;
import com.pragma.user.application.handler.IUserHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthRestController {

    private final IUserHandler userHandler;

    @PostMapping("/")
    public ResponseEntity<LoginResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userHandler.authenticate(loginRequest));
    }
}
