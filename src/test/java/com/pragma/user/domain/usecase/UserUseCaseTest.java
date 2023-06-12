package com.pragma.user.domain.usecase;

import com.pragma.user.domain.model.Role;
import com.pragma.user.domain.model.User;
import com.pragma.user.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserUseCaseTest {

    @InjectMocks
    private UserUseCase userUseCase;

    @Mock
    private IUserPersistencePort userPersistencePort;

    private User user01;

    @BeforeEach
    void setUp() {
        user01 = new User(1, "Abel", "Asta", "11111111", "+51999999999",
                new Date(), "abel@mail.com", "123", new Role(2, "", ""));
    }

    @Test
    void saveUser() {

        //  Given
        Mockito.doNothing().when(userPersistencePort).saveUser(Mockito.any(User.class));

        //  When
        userUseCase.saveUser(user01);

        //  Then
        Mockito.verify(userPersistencePort, Mockito.times(1)).saveUser(Mockito.any(User.class));
    }

    @Test
    void getAllUsers() {
        User user02 = new User(1, "Betty", "Bellido", "22222222", "+51999999999",
                new Date(), "betty@mail.com", "123", new Role(2, "", ""));

        List<User> data = List.of(user01, user02);

        //  Given
        Mockito.when(userPersistencePort.getAllUsers()).thenReturn(data);

        //  When
        List<User> userList = userUseCase.getAllUsers();

        //  Then
        Mockito.verify(userPersistencePort, Mockito.times(1)).getAllUsers();
        assertEquals("11111111", userList.get(0).getDocumentNumber());
        assertEquals("Betty", userList.get(1).getName());
        assertEquals(2, userList.size());

    }

    @Test
    void getUserById() {
        //  Given
        Mockito.when(userPersistencePort.getUserById(1)).thenReturn(user01);

        //  When
        User userTest = userUseCase.getUserById(1);

        //  Then
        Mockito.verify(userPersistencePort, Mockito.times(1)).getUserById(1);
        assertEquals("Abel", userTest.getName());
    }

    @Test
    void deleteUserById() {
        //  Given
        Mockito.doNothing().when(userPersistencePort).deleteUserById(Mockito.anyInt());

        //  When
        userUseCase.deleteUserById(Mockito.anyInt());

        //  Then
        Mockito.verify(userPersistencePort, Mockito.times(1)).deleteUserById(Mockito.anyInt());
    }
}