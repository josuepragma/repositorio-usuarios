package com.pragma.user.infrastructure.output.jpa.repository;

import com.pragma.user.infrastructure.output.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByName(String name);

    void deleteByName(String name);
}
