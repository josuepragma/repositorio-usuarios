package com.pragma.user.application.handler;

import com.pragma.user.application.dto.request.EmployeeRequestDto;
import com.pragma.user.application.dto.request.LoginRequest;
import com.pragma.user.application.dto.response.LoginResponse;
import com.pragma.user.application.dto.request.OwnerRequestDto;
import com.pragma.user.application.dto.response.UserResponseDto;
import com.pragma.user.application.mapper.IEmployeeRequestMapper;
import com.pragma.user.application.mapper.IOwnerRequestMapper;
import com.pragma.user.application.mapper.IUserResponseMapper;
import com.pragma.user.domain.api.IRoleServicePort;
import com.pragma.user.domain.api.IUserServicePort;
import com.pragma.user.domain.model.Role;
import com.pragma.user.domain.model.User;
import com.pragma.user.infrastructure.exception.InvalidEmailException;
import com.pragma.user.infrastructure.exception.InvalidPasswordException;
import com.pragma.user.infrastructure.security.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserHandler implements IUserHandler {

    private final IUserServicePort userServicePort;
    private final IRoleServicePort roleServicePort;

    private final IOwnerRequestMapper ownerRequestMapper;
    private final IEmployeeRequestMapper employeeRequestMapper;
    private final IUserResponseMapper ownerResponseMapper;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public void saveOwner(OwnerRequestDto ownerRequestDto) {
        Integer idRole = 2;
        Role role = roleServicePort.getRoleById(idRole);

        User user = ownerRequestMapper.toUser(ownerRequestDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(role);

        userServicePort.saveUser(user);
    }

    @Override
    public void saveEmployee(EmployeeRequestDto employeeRequestDto) {
        Integer idRole = 3;
        Role role = roleServicePort.getRoleById(idRole);

        User user = employeeRequestMapper.toUser(employeeRequestDto);
        user.setBirthdate(new Date());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(role);

        userServicePort.saveUser(user);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return ownerResponseMapper.toResponseList(userServicePort.getAllUsers());
    }

    @Override
    public UserResponseDto getUserById(Integer id) {
        User user = userServicePort.getUserById(id);

        return ownerResponseMapper.toResponse(user);
    }

    @Override
    public void deleteUserById(Integer id) {
        userServicePort.deleteUserById(id);
    }

    @Override
    public LoginResponse authenticate(LoginRequest loginRequest) {
        User user = userServicePort.getUserByEmail(loginRequest.getEmail());
        if (user == null) {
            throw new InvalidEmailException("Email doesn't exist in Database");
        }

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidPasswordException("Password entered is wrong");
        }

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtService.generateToken(JwtService
                .createUserDetails(user.getRole().getName(), user.getId().toString())));
        loginResponse.setType("Bearer");

        return loginResponse;
    }
}
