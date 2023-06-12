package com.pragma.user.infrastructure.output.jpa.adapter;

import com.pragma.user.domain.model.Role;
import com.pragma.user.domain.spi.IRolePersistencePort;
import com.pragma.user.infrastructure.exception.RoleNotFoundException;
import com.pragma.user.infrastructure.output.jpa.entity.RoleEntity;
import com.pragma.user.infrastructure.output.jpa.mapper.IRoleEntityMapper;
import com.pragma.user.infrastructure.output.jpa.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class RoleJpaAdapter implements IRolePersistencePort {

    private final IRoleRepository roleRepository;
    private final IRoleEntityMapper roleEntityMapper;

    @Override
    public void saveRole(Role role) {
        roleEntityMapper.toRole(roleRepository.save(roleEntityMapper.toEntity(role)));
    }

    @Override
    public List<Role> getAllRoles() {
        List<RoleEntity> roleEntityList = roleRepository.findAll();
        if (roleEntityList.isEmpty()) {
            throw new RoleNotFoundException("Role Not Found Exception");
        }
        return roleEntityMapper.toRoleList(roleEntityList);
    }

    @Override
    public Role getRoleById(Integer roleId) {
        return roleEntityMapper.toRole(roleRepository.findById(roleId)
                .orElseThrow(() -> new RoleNotFoundException("Role Not Found Exception")));
    }

    @Override
    public Role getRoleByName(String roleName) {
        return roleEntityMapper.toRole(roleRepository.findByName(roleName)
                .orElseThrow(() -> new RoleNotFoundException("Role Not Found Exception")));
    }

    @Override
    public void updateRole(Role role) {
        roleRepository.save(roleEntityMapper.toEntity(role));
    }

    @Override
    public void deleteRole(Integer roleId) {
        roleRepository.deleteById(roleId);
    }
}
