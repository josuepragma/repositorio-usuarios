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

    @GetMapping("/{id}")
    public ResponseEntity<OwnerResponseDto> getUserByName(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(ownerHandler.getOwnerById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@Valid @RequestBody OwnerRequestDto ownerRequestDto,
                                           @PathVariable(name = "id") Integer id) {
        ownerHandler.updateOwner(ownerRequestDto, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        ownerHandler.deleteOwnerById(id);
        return ResponseEntity.noContent().build();
    }
}
