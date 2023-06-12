package com.pragma.user.domain.usecase;

import com.pragma.user.domain.model.Role;
import com.pragma.user.domain.spi.IRolePersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RoleUseCaseTest {

    @InjectMocks
    private RoleUseCase roleUseCase;
    @Mock
    private IRolePersistencePort rolePersistencePort;

    Role role01, role02, role03, role04;

    @BeforeEach
    void setUp() {
        role01 = new Role(1, "Admin", "");
        role02 = new Role(2, "Owner", "");
        role03 = new Role(3, "Employee", "");
        role04 = new Role(4, "Client", "");
    }

    @Test
    void saveRole() {
        Role roleTest = new Role(5, "Tester", "");
        //  Given
        Mockito.doNothing().when(rolePersistencePort).saveRole(Mockito.any(Role.class));

        //  When
        roleUseCase.saveRole(roleTest);

        //  Then
        Mockito.verify(rolePersistencePort, Mockito.times(1)).saveRole(Mockito.any(Role.class));
    }

    @Test
    void getAllRoles() {
        List<Role> data = List.of(role01, role02, role03, role04);

        //  Given
        Mockito.when(rolePersistencePort.getAllRoles()).thenReturn(data);

        //  When
        List<Role> roleList = roleUseCase.getAllRoles();

        //  Then
        Mockito.verify(rolePersistencePort, Mockito.times(1)).getAllRoles();
        assertEquals("Client", roleList.get(3).getName());
        assertFalse(roleList.isEmpty());
        assertEquals(4, roleList.size());
    }

    @Test
    void getRoleById() {
        //  Given
        Mockito.when(rolePersistencePort.getRoleById(1)).thenReturn(role01);
        Mockito.when(rolePersistencePort.getRoleById(2)).thenReturn(role02);

        //  When
        Role roleTest01 = roleUseCase.getRoleById(1);
        Role roleTest02 = roleUseCase.getRoleById(2);

        //  Then
        Mockito.verify(rolePersistencePort, Mockito.times(2)).getRoleById(Mockito.anyInt());
        assertEquals("Admin", roleTest01.getName());
        assertEquals("Owner", roleTest02.getName());
    }

    @Test
    void getRoleByName() {
        //  Given
        Mockito.when(rolePersistencePort.getRoleByName("Client")).thenReturn(role04);

        //  When
        Role roleTest = roleUseCase.getRoleByName("Client");

        //  Then
        Mockito.verify(rolePersistencePort).getRoleByName(Mockito.anyString());
        assertEquals("Client", roleTest.getName());
        assertEquals(4, roleTest.getId());
    }

    @Test
    void updateRole() {
        Role role = new Role(4, "Customer", "");

        //  Given
        Mockito.doNothing().when(rolePersistencePort).updateRole(Mockito.any(Role.class));

        //  When
        roleUseCase.updateRole(role);

        //  Then
        Mockito.verify(rolePersistencePort, Mockito.times(1)).updateRole(Mockito.any(Role.class));
    }

    @Test
    void deleteRole() {
        //  Given
        Mockito.doNothing().when(rolePersistencePort).deleteRole(Mockito.anyInt());

        //  When
        roleUseCase.deleteRole(4);
        roleUseCase.deleteRole(3);
        roleUseCase.deleteRole(2);

        //  Then
        Mockito.verify(rolePersistencePort, Mockito.times(3)).deleteRole(Mockito.anyInt());
        Mockito.verify(rolePersistencePort, Mockito.times(1)).deleteRole(4);
        Mockito.verify(rolePersistencePort, Mockito.times(1)).deleteRole(3);
        Mockito.verify(rolePersistencePort, Mockito.times(1)).deleteRole(2);
        Mockito.verify(rolePersistencePort, Mockito.times(0)).deleteRole(1);
    }
}