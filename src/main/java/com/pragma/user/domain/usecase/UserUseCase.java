package com.pragma.user.domain.usecase;

import com.pragma.user.domain.api.IUserServicePort;
import com.pragma.user.domain.model.User;
import com.pragma.user.domain.spi.IUserPersistencePort;

import java.util.List;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    public UserUseCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public void saveUser(User user) {
        userPersistencePort.saveUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userPersistencePort.getAllUsers();
    }

    @Override
    public User getUserById(Integer id) {
        return userPersistencePort.getUserById(id);
    }

    @Override
    public void updateUser(User user) {
        userPersistencePort.updateUser(user);
    }

    @Override
    public void deleteUserById(Integer id) {
        userPersistencePort.deleteUserById(id);
    }
}
