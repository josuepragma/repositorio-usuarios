package com.pragma.user.application.mapper;

import com.pragma.user.application.dto.OwnerRequestDto;
import com.pragma.user.domain.model.Role;
import com.pragma.user.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOwnerRequestMapper {
    User toUser(OwnerRequestDto ownerRequestDto);

    Role toRole(OwnerRequestDto ownerRequestDto);
}
