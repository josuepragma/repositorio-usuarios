package com.pragma.user.infrastructure.input.rest;

import com.pragma.user.application.dto.request.EmployeeRequestDto;
import com.pragma.user.application.dto.request.OwnerRequestDto;
import com.pragma.user.application.dto.response.UserResponseDto;
import com.pragma.user.application.handler.IUserHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//@Tag(name = "message", description = "USER REST API")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestController {

    private final IUserHandler userHandler;

    @Operation(summary = "Save new owner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. Owner created successfully", content = @Content),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST. JSON request is invalid", content = @Content),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED. User is not authorized", content = @Content)
    })
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping(value = "/owner/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveOwner(@Valid @RequestBody OwnerRequestDto ownerRequestDto) {
        userHandler.saveOwner(ownerRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Save new employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. Employee created successfully", content = @Content),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST. JSON request is invalid", content = @Content),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED. User is not authorized", content = @Content)
    })
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping(value = "/employee/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveEmployee(@Valid @RequestBody EmployeeRequestDto employeeRequestDto) {
        userHandler.saveEmployee(employeeRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "List all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. Users found successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST. Request is invalid", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Users not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED. User is not authorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN. User has no permissions", content = @Content)
    })
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(userHandler.getAllUsers());
    }

    @Operation(summary = "Get User by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. User found successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST. Request is invalid", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. User not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED. User is not authorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN. User has no permissions", content = @Content)
    })
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(userHandler.getUserById(id));
    }

    @Operation(summary = "Delete user by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "NO CONTENT. User deleted successfully", content = @Content),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST. Request is invalid", content = @Content),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED. User is not authorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN. User has no permissions", content = @Content)
    })
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userHandler.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
