package com.pragma.user.application.mapper;

import com.pragma.user.application.dto.request.EmployeeRequestDto;
import com.pragma.user.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IEmployeeRequestMapper {

    User toUser(EmployeeRequestDto employeeRequestDto);
}
