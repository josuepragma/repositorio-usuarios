package com.pragma.user.application.mapper;

import com.pragma.user.application.dto.OwnerResponseDto;
import com.pragma.user.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.text.SimpleDateFormat;
import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOwnerResponseMapper {
    default OwnerResponseDto toResponse(User user) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String strBirthdate = formatter.format(user.getBirthdate());

        OwnerResponseDto ownerResponseDto = new OwnerResponseDto();
        ownerResponseDto.setId(user.getId());
        ownerResponseDto.setName(user.getName());
        ownerResponseDto.setSurname(user.getSurname());
        ownerResponseDto.setDocumentNumber(user.getDocumentNumber());
        ownerResponseDto.setBirthdate(strBirthdate);
        ownerResponseDto.setPhone(user.getPhone());
        ownerResponseDto.setEmail(user.getEmail());
        ownerResponseDto.setPassword(user.getPassword());
        ownerResponseDto.setIdRole(user.getRole().getId());
        ownerResponseDto.setNameRole(user.getRole().getName());

        return ownerResponseDto;
    }

    List<OwnerResponseDto> toResponseList(List<User> userList);
}
