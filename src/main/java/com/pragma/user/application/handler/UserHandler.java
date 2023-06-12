package com.pragma.user.application.handler;

import com.pragma.user.application.dto.LoginRequest;
import com.pragma.user.application.dto.LoginResponse;
import com.pragma.user.application.dto.OwnerRequestDto;
import com.pragma.user.application.dto.OwnerResponseDto;
import com.pragma.user.application.mapper.IOwnerRequestMapper;
import com.pragma.user.application.mapper.IOwnerResponseMapper;
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

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserHandler implements IUserHandler {

    private final IUserServicePort userServicePort;
    private final IRoleServicePort roleServicePort;
    private final IOwnerRequestMapper ownerRequestMapper;
    private final IOwnerResponseMapper ownerResponseMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public void saveOwner(OwnerRequestDto ownerRequestDto) {
        Integer idRole = 2;
        Role role = roleServicePort.getRoleById(idRole);

        User user = ownerRequestMapper.toUser(ownerRequestDto);
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userServicePort.saveUser(user);
    }

    @Override
    public List<OwnerResponseDto> getAllOwners() {
//        return ownerResponseMapper.toResponseList(userServicePort.getAllUsers(), roleServicePort.getAllRoles());
        return ownerResponseMapper.toResponseList(userServicePort.getAllUsers());
    }

    @Override
    public OwnerResponseDto getOwnerById(Integer id) {
        User user = userServicePort.getUserById(id);
//        Role role = roleServicePort.getRoleById(user.getRole().getId());

        return ownerResponseMapper.toResponse(user);
    }

    @Override
    public void deleteOwnerById(Integer id) {
        userServicePort.deleteUserById(id);
    }

    @Override
    public LoginResponse authenticate(LoginRequest loginRequest) {
        User user = userServicePort.getUserByEmail(loginRequest.getEmail());
        if(user == null ){
            throw new InvalidEmailException("Email doesn't exist in Database");
        }

        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidPasswordException("Password entered is wrong");
        }

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtService.generateToken(JwtService
                .createUserDetails(user.getRole().getName(), user.getId().toString())));
        loginResponse.setType("Bearer");

        return loginResponse;
    }
}
