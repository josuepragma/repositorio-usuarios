package com.pragma.user.domain.usecase;

import com.pragma.user.domain.api.IRoleServicePort;
import com.pragma.user.domain.model.Role;
import com.pragma.user.domain.spi.IRolePersistencePort;

import java.util.List;

public class RoleUseCase implements IRoleServicePort {

    private final IRolePersistencePort rolePersistencePort;
    public RoleUseCase(IRolePersistencePort rolePersistencePort) {
        this.rolePersistencePort = rolePersistencePort;
    }

    @Override
    public void saveRole(Role role) {
        rolePersistencePort.saveRole(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return rolePersistencePort.getAllRoles();
    }

    @Override
    public Role getRoleById(Integer roleId) {
        return rolePersistencePort.getRoleById(roleId);
    }

    @Override
    public Role getRoleByName(String roleName) {
        return rolePersistencePort.getRoleByName(roleName);
    }

    @Override
    public void updateRole(Role role) {
        rolePersistencePort.updateRole(role);
    }

    @Override
    public void deleteRole(Integer roleId) {
        rolePersistencePort.deleteRole(roleId);
    }
}
