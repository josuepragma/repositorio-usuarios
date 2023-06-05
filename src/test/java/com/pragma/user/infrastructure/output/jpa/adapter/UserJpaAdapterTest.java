package com.pragma.user.infrastructure.output.jpa.adapter;

import com.pragma.user.domain.model.User;
import com.pragma.user.infrastructure.exception.UserNotFoundException;
import com.pragma.user.infrastructure.output.jpa.entity.UserEntity;
import com.pragma.user.infrastructure.output.jpa.mapper.IUserEntityMapper;
import com.pragma.user.infrastructure.output.jpa.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class UserJpaAdapterTest {
    @Mock
    IUserRepository userRepository;
    @Mock
    IUserEntityMapper userEntityMapper;

    @InjectMocks
    UserJpaAdapter userJpaAdapter;

    private User newUser;

    @BeforeEach
    void setup() {
        newUser = new User(1, "Luis", "Robles", "11111111",
                "+51999999999", new Date(), "luis@pragma.com.co", "password", 2);
    }

    @Test
    void saveUser() {
        //  Given
        Mockito.when(userRepository.save(any())).thenReturn(null);
        Mockito.when(userEntityMapper.toEntity(any())).thenReturn(null);

        //  When
        userJpaAdapter.saveUser(newUser);

        //  Then
        Mockito.verify(userRepository, Mockito.times(1)).save(any());
        Mockito.verify(userEntityMapper, Mockito.times(1)).toEntity(any());
    }

    @Test
    void getAllUsers() {
        User anotherUser = new User(2, "Maria", "Aco", "22222222",
                "+51999999999", new Date(), "maria@pragma.com.co", "password", 2);

        //  Given
        Mockito.when(userRepository.findAll()).thenReturn(getListUserEntity());
        Mockito.when(userEntityMapper.toUserList(any())).thenReturn(List.of(newUser, anotherUser));
//        Mockito.when(userEntityMapper.toUserList(any())).thenReturn(getListUser());

        //  When
        List<User> users = userJpaAdapter.getAllUsers();

        //  Then
        Mockito.verify(userRepository, Mockito.times(1)).findAll();
        Mockito.verify(userEntityMapper, Mockito.times(1)).toUserList(any());
        assertEquals(2, users.size());
        assertEquals("Luis", users.get(0).getName());
        assertEquals("Maria", users.get(1).getName());
        assertFalse(users.isEmpty());
    }

    @Test
    void getUserByName() {
        UserEntity userEntity = new UserEntity(1, "Luis", "Robles", "11111111",
                "+51999999999", new Date(), "luis@pragma.com.co", "password", 2);

        //  Given
        Mockito.when(userRepository.findByName("Luis")).thenReturn(Optional.of(userEntity));
        Mockito.when(userEntityMapper.toUser(any())).thenReturn(newUser);

        //  When
        User user = userJpaAdapter.getUserByName("Luis");

        //  Then
        Mockito.verify(userRepository, Mockito.times(1)).findByName(any());
        Mockito.verify(userEntityMapper, Mockito.times(1)).toUser(any());
        assertEquals("Luis", user.getName());
        assertEquals("11111111", user.getDocumentNumber());
    }

    @Test
    void getUserByName_UserNotFoundException() {
        UserEntity userEntity = new UserEntity(1, "Luis", "Robles", "11111111",
                "+51999999999", new Date(), "luis@pragma.com.co", "password", 2);

         //  Given
        Mockito.when(userRepository.findByName("Pedro")).thenReturn(Optional.empty());

        //  When and Then
        Mockito.verify(userRepository, Mockito.times(0)).findByName(any());
        Mockito.verify(userEntityMapper, Mockito.times(0)).toUser(any());
        assertThrows(UserNotFoundException.class, () -> userJpaAdapter.getUserByName("Pedro"));
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUserByName() {
    }

    @Test
    void deleteUserByDocumentNumber() {
    }

    public List<UserEntity> getListUserEntity() {
        List<UserEntity> userEntityList = new ArrayList<>();

        UserEntity userEntity01 = new UserEntity(1, "Luis", "Robles", "11111111",
                "+51999999999", new Date(), "luis@pragma.com.co", "password", 2);
        UserEntity userEntity02 = new UserEntity(2, "Maria", "Aco", "22222222",
                "+51999999999", new Date(), "maria@pragma.com.co", "password", 2);

        userEntityList.add(userEntity01);
        userEntityList.add(userEntity02);

        return userEntityList;
    }

    public List<User> getListUser() {
        List<User> userList = new ArrayList<>();

        User user01 = new User(1, "Luis", "Robles", "11111111",
                "+51999999999", new Date(), "luis@pragma.com.co", "password", 2);
        User user02 = new User(2, "Maria", "Aco", "22222222",
                "+51999999999", new Date(), "maria@pragma.com.co", "password", 2);

        userList.add(user01);
        userList.add(user02);

        return userList;
    }
}