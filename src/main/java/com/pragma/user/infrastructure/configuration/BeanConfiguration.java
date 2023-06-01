package com.pragma.user.infrastructure.configuration;

import com.pragma.user.domain.api.IRoleServicePort;
import com.pragma.user.domain.api.IUserServicePort;
import com.pragma.user.domain.spi.IRolePersistencePort;
import com.pragma.user.domain.spi.IUserPersistencePort;
import com.pragma.user.domain.usecase.RoleUseCase;
import com.pragma.user.domain.usecase.UserUseCase;
import com.pragma.user.infrastructure.output.jpa.adapter.RoleJpaAdapter;
import com.pragma.user.infrastructure.output.jpa.adapter.UserJpaAdapter;
import com.pragma.user.infrastructure.output.jpa.mapper.IRoleEntityMapper;
import com.pragma.user.infrastructure.output.jpa.mapper.IUserEntityMapper;
import com.pragma.user.infrastructure.output.jpa.repository.IRoleRepository;
import com.pragma.user.infrastructure.output.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final IRoleRepository roleRepository;
    private final IRoleEntityMapper roleEntityMapper;

    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserJpaAdapter(userRepository, userEntityMapper);
    }

    @Bean
    public IUserServicePort userServicePort() {
        return new UserUseCase(userPersistencePort());
    }

    @Bean
    public IRolePersistencePort rolePersistencePort() {
        return new RoleJpaAdapter(roleRepository, roleEntityMapper);
    }

    @Bean
    public IRoleServicePort roleServicePort() {
        return new RoleUseCase(rolePersistencePort());
    }

}
