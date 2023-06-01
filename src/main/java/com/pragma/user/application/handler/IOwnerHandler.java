package com.pragma.user.application.handler;

import com.pragma.user.application.dto.OwnerRequestDto;
import com.pragma.user.application.dto.OwnerResponseDto;

import java.util.List;

public interface IOwnerHandler {

    void saveOwner(OwnerRequestDto ownerRequestDto);

    List<OwnerResponseDto> getAllOwners();

    OwnerResponseDto getOwner(String name);

    void updateOwner(OwnerRequestDto ownerRequestDto);

    void deleteOwner(String name);
}
