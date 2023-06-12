package com.pragma.user.application.mapper;

import com.pragma.user.application.dto.response.UserResponseDto;
import com.pragma.user.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserResponseMapper {

    @Mapping(target = "idRole", source = "user.role.id")
    @Mapping(target = "nameRole", source = "user.role.name")
    UserResponseDto toResponse(User user);

    @Mapping(target = "idRole", source = "user.role.id")
    @Mapping(target = "nameRole", source = "user.role.name")
    List<UserResponseDto> toResponseList(List<User> userList);
}
