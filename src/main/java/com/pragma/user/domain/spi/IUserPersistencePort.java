package com.pragma.user.domain.spi;

import com.pragma.user.domain.model.User;

import java.util.List;

public interface IUserPersistencePort {
    void saveUser(User user);

    List<User> getAllUsers();

    User getUserByName(String name);

    User getUserByDocumentNumber(String documentNumber);

    void updateUser(User user);

    void deleteUserByName(String name);

    void deleteUserByDocumentNumber(String documentNumber);
}
