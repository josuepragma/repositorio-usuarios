package com.pragma.user.application.mapper;

import com.pragma.user.application.dto.request.CustomerRequestDto;
import com.pragma.user.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICustomerRequestMapper {
    User toUser(CustomerRequestDto customerRequestDto);
}
