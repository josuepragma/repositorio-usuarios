package com.pragma.user.domain.spi;

import com.pragma.user.domain.model.Role;

import java.util.List;

public interface IRolePersistencePort {
    void saveRole(Role role);

    List<Role> getAllRoles();

    Role getRoleById(Integer roleId);

    Role getRoleByName(String roleName);

    void updateRole(Role role);

    void deleteRole(Integer roleId);
}
