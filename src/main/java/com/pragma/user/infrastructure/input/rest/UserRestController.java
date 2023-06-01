package com.pragma.user.infrastructure.input.rest;

import com.pragma.user.application.dto.OwnerRequestDto;
import com.pragma.user.application.dto.OwnerResponseDto;
import com.pragma.user.application.handler.IOwnerHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestController {

    private final IOwnerHandler ownerHandler;

    @PostMapping("/")
    public ResponseEntity<Void> saveUser(@Valid @RequestBody OwnerRequestDto ownerRequestDto) {
        ownerHandler.saveOwner(ownerRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    public ResponseEntity<List<OwnerResponseDto>> getAllUsers() {
        return ResponseEntity.ok(ownerHandler.getAllOwners());
    }

    @GetMapping("/{name}")
    public ResponseEntity<OwnerResponseDto> getUserByName(@PathVariable(name = "name") String name) {
        return ResponseEntity.ok(ownerHandler.getOwner(name));
    }

    @PutMapping("/")
    public ResponseEntity<Void> updateUser(@RequestBody OwnerRequestDto ownerRequestDto) {
        ownerHandler.updateOwner(ownerRequestDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteUser(@PathVariable String name) {
        ownerHandler.deleteOwner(name);
        return ResponseEntity.noContent().build();
    }
}
