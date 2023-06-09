package com.pragma.user.domain.spi;

import com.pragma.user.domain.model.User;

import java.util.List;

public interface IUserPersistencePort {
    void saveUser(User user);

    List<User> getAllUsers();

    User getUserById(Integer id);

    User getUserByEmail(String email);

    void deleteUserById(Integer id);
}
