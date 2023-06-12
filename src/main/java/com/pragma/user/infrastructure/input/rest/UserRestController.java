package com.pragma.user.infrastructure.input.rest;

import com.pragma.user.application.dto.OwnerRequestDto;
import com.pragma.user.application.dto.OwnerResponseDto;
import com.pragma.user.application.handler.IUserHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    private final IUserHandler ownerHandler;

    @Operation(summary = "Save new owner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Owner created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Request JSON error", content = @Content),
            @ApiResponse(responseCode = "401", description = "No authorized", content = @Content)
    })
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping(value = "/owner/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveOwner(@Valid @RequestBody OwnerRequestDto ownerRequestDto) {
        ownerHandler.saveOwner(ownerRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "List all owners")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Owners found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OwnerResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Request JSON error", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)
    })
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(value = "/owner/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OwnerResponseDto>> getAllUsers() {
        return ResponseEntity.ok(ownerHandler.getAllOwners());
    }

    @Operation(summary = "Get owner by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Owner found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OwnerResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Request JSON error", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)
    })
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(value = "/owner/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OwnerResponseDto> getUserByName(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(ownerHandler.getOwnerById(id));
    }

    @Operation(summary = "Delete owner by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Owner found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OwnerResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Request JSON error", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)
    })
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        ownerHandler.deleteOwnerById(id);
        return ResponseEntity.noContent().build();
    }
}
