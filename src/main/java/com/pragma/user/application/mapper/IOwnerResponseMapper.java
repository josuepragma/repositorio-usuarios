package com.pragma.user.application.mapper;

import com.pragma.user.application.dto.OwnerResponseDto;
import com.pragma.user.domain.model.Role;
import com.pragma.user.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOwnerResponseMapper {
    default OwnerResponseDto toResponse(User user, Role role) {
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
        ownerResponseDto.setRole(role.getName());

        return ownerResponseDto;
    }

    default List<OwnerResponseDto> toResponseList(List<User> userList, List<Role> roleList) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        return userList.stream()
                .map(userParam -> {
                    OwnerResponseDto ownerResponseDto = new OwnerResponseDto();
                    ownerResponseDto.setId(userParam.getId());
                    ownerResponseDto.setName(userParam.getName());
                    ownerResponseDto.setSurname(userParam.getSurname());
                    ownerResponseDto.setDocumentNumber(userParam.getDocumentNumber());
                    ownerResponseDto.setBirthdate(formatter.format(userParam.getBirthdate()));
                    ownerResponseDto.setPhone(userParam.getPhone());
                    ownerResponseDto.setEmail(userParam.getEmail());
                    ownerResponseDto.setPassword(userParam.getPassword());
                    ownerResponseDto.setRole(roleList.stream().filter(roleParam -> roleParam.getId().equals(userParam.getIdRole())).findFirst().orElse(null).getName());
                    return ownerResponseDto;
                }).collect(Collectors.toList());
    }
}
