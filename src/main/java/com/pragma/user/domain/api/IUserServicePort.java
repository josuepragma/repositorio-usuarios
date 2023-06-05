package com.pragma.user.domain.api;

import com.pragma.user.domain.model.User;

import java.util.List;

public interface IUserServicePort {

    void saveUser(User user);

    List<User> getAllUsers();

    User getUserById(Integer id);

    void updateUser(User user);

    void deleteUserById(Integer id);

}
