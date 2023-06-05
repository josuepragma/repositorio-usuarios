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
    private final IOwnerRequestMapper ownerRequestMapper;
    private final IOwnerResponseMapper ownerResponseMapper;

    @Override
    public void saveOwner(OwnerRequestDto ownerRequestDto) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String roleName = "Propietario";

        Role role = roleServicePort.getRoleByName(roleName);
        User user = ownerRequestMapper.toUser(ownerRequestDto);
        user.setIdRole(role.getId());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userServicePort.saveUser(user);
    }

    @Override
    public List<OwnerResponseDto> getAllOwners() {
        return ownerResponseMapper.toResponseList(userServicePort.getAllUsers(), roleServicePort.getAllRoles());
    }

    @Override
    public OwnerResponseDto getOwnerById(Integer id) {
        User user = userServicePort.getUserById(id);
        Role role = roleServicePort.getRoleById(user.getIdRole());

        return ownerResponseMapper.toResponse(user, role);
    }

    @Override
    public void updateOwner(OwnerRequestDto ownerRequestDto, Integer id) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        User modifiedUser = userServicePort.getUserById(id);
        modifiedUser.setName(ownerRequestDto.getName());
        modifiedUser.setSurname(ownerRequestDto.getSurname());
        modifiedUser.setDocumentNumber(ownerRequestDto.getDocumentNumber());
        modifiedUser.setBirthdate(ownerRequestDto.getBirthdate());
        modifiedUser.setPhone(ownerRequestDto.getPhone());
        modifiedUser.setEmail(ownerRequestDto.getEmail());
        modifiedUser.setPassword(passwordEncoder.encode(ownerRequestDto.getPassword()));
        modifiedUser.setIdRole(2);

        userServicePort.updateUser(modifiedUser);
    }

    @Override
    public void deleteOwnerById(Integer id) {
        userServicePort.deleteUserById(id);
    }
}
