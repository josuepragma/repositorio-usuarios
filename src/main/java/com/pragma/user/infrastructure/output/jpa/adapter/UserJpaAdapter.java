package com.pragma.user.infrastructure.output.jpa.adapter;

import com.pragma.user.domain.model.User;
import com.pragma.user.domain.spi.IUserPersistencePort;
import com.pragma.user.infrastructure.exception.NoDataFoundException;
import com.pragma.user.infrastructure.exception.UserAlreadyExistsException;
import com.pragma.user.infrastructure.exception.UserNotFoundException;
import com.pragma.user.infrastructure.output.jpa.entity.UserEntity;
import com.pragma.user.infrastructure.output.jpa.mapper.IUserEntityMapper;
import com.pragma.user.infrastructure.output.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;

    @Override
    public void saveUser(User user) {
        if (userRepository.findByName(user.getName()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        userRepository.save(userEntityMapper.toEntity(user));
    }

    @Override
    public List<User> getAllUsers() {
        List<UserEntity> userEntityList = userRepository.findAll();
        if (userEntityList.isEmpty()) {
            throw new NoDataFoundException();
        }
        return userEntityMapper.toUserList(userEntityList);
    }

    @Override
    public User getUserByName(String name) {
        return userEntityMapper.toUser(userRepository.findByName(name)
                .orElseThrow(UserNotFoundException::new));
    }

    @Override
    public User getUserByDocumentNumber(String documentNumber) {
        return null;
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(userEntityMapper.toEntity(user));
    }

    @Override
    public void deleteUserByName(String name) {
        userRepository.deleteByName(name);
    }

    @Override
    public void deleteUserByDocumentNumber(String documentNumber) {

    }
}
