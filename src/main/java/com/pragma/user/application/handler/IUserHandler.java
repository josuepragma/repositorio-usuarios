package com.pragma.user.application.handler;

import com.pragma.user.application.dto.request.CustomerRequestDto;
import com.pragma.user.application.dto.request.EmployeeRequestDto;
import com.pragma.user.application.dto.request.LoginRequest;
import com.pragma.user.application.dto.response.LoginResponse;
import com.pragma.user.application.dto.request.OwnerRequestDto;
import com.pragma.user.application.dto.response.UserResponseDto;

import java.util.List;

public interface IUserHandler {

    void saveOwner(OwnerRequestDto ownerRequestDto);

    void saveEmployee(EmployeeRequestDto employeeRequestDto);

    void saveCustomer(CustomerRequestDto customerRequestDto);

    List<UserResponseDto> getAllUsers();

    UserResponseDto getUserById(Integer id);

    void deleteUserById(Integer id);

    public LoginResponse authenticate(LoginRequest loginRequest);

}
