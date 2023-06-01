package com.pragma.user.application.handler;

import com.pragma.user.application.dto.OwnerRequestDto;
import com.pragma.user.application.dto.OwnerResponseDto;
import com.pragma.user.application.mapper.IOwnerRequestMapper;
import com.pragma.user.application.mapper.IOwnerResponseMapper;
import com.pragma.user.domain.api.IRoleServicePort;
import com.pragma.user.domain.api.IUserServicePort;
import com.pragma.user.domain.model.Role;
import com.pragma.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OwnerHandler implements IOwnerHandler {

    private final IUserServicePort userServicePort;
    private final IRoleServicePort roleServicePort;
    private final IOwnerRequestMapper userRequestMapper;
    private final IOwnerResponseMapper userResponseMapper;

    @Override
    public void saveOwner(OwnerRequestDto ownerRequestDto) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String roleName = "Propietario";

        Role role = roleServicePort.getRoleByName(roleName);
        User user = userRequestMapper.toUser(ownerRequestDto);
        user.setIdRole(role.getId());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userServicePort.saveUser(user);
    }

    @Override
    public List<OwnerResponseDto> getAllOwners() {
        return userResponseMapper.toResponseList(userServicePort.getAllUsers(), roleServicePort.getAllRoles());
    }

    @Override
    public OwnerResponseDto getOwner(String name) {
        User user = userServicePort.getUserByName(name);
        Role role = roleServicePort.getRoleById(user.getIdRole());

        return userResponseMapper.toResponse(user, role);
    }

    @Override
    public void updateOwner(OwnerRequestDto ownerRequestDto) {
        User oldUser = userServicePort.getUserByName(ownerRequestDto.getName());
        Role newRole = userRequestMapper.toRole(ownerRequestDto);
        newRole.setId(oldUser.getId());
        roleServicePort.updateRole(newRole);

        User newUser = userRequestMapper.toUser(ownerRequestDto);
        newUser.setId(oldUser.getId());
        newUser.setIdRole(oldUser.getIdRole());

        userServicePort.updateUser(newUser);
    }

    @Override
    public void deleteOwner(String name) {
        User user = userServicePort.getUserByName(name);
        roleServicePort.deleteRole(user.getIdRole());

        userServicePort.deleteUserByName(name);
    }
}
