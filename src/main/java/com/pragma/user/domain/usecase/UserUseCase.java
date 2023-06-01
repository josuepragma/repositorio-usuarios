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
    public User getUserByName(String name) {
        return userPersistencePort.getUserByName(name);
    }

    @Override
    public User getUserByDocumentNumber(String documentNumber) {
        return userPersistencePort.getUserByDocumentNumber(documentNumber);
    }

    @Override
    public void updateUser(User user) {
        userPersistencePort.updateUser(user);
    }

    @Override
    public void deleteUserByName(String name) {
        userPersistencePort.deleteUserByName(name);
    }

    @Override
    public void deleteUserByDocumentNumber(String documentNumber) {
        userPersistencePort.deleteUserByDocumentNumber(documentNumber);
    }
}
