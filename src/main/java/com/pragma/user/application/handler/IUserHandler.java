package com.pragma.user.application.handler;

import com.pragma.user.application.dto.LoginRequest;
import com.pragma.user.application.dto.LoginResponse;
import com.pragma.user.application.dto.OwnerRequestDto;
import com.pragma.user.application.dto.OwnerResponseDto;

import java.util.List;

public interface IUserHandler {

    void saveOwner(OwnerRequestDto ownerRequestDto);

    List<OwnerResponseDto> getAllOwners();

    OwnerResponseDto getOwnerById(Integer id);

    void deleteOwnerById(Integer id);

    public LoginResponse authenticate(LoginRequest loginRequest);

}
