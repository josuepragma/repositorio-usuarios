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
        if (userRepository.findByDocumentNumber(user.getDocumentNumber()).isPresent()) {
            throw new UserAlreadyExistsException("User already exists with document N°: " + user.getDocumentNumber());
        }
        userRepository.save(userEntityMapper.toEntity(user));
    }

    @Override
    public List<User> getAllUsers() {
        List<UserEntity> userEntityList = userRepository.findAll();
        if (userEntityList.isEmpty()) {
            throw new NoDataFoundException("No Data Found Exception");
        }
        return userEntityMapper.toUserList(userEntityList);
    }

    @Override
    public User getUserById(Integer id) {
        return userEntityMapper.toUser(userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User Not Found for parameter { id = " + id + " }")));
    }

    @Override
    public User getUserByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User Not Found for email = " + email));

        return userEntityMapper.toUser(userEntity);
    }
    @Override
    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }

}
