package com.pragma.user.infrastructure.output.jpa.entity;

import com.pragma.user.domain.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;
    String surname;
    String documentNumber;
    String phone;
    Date birthdate;

    @Column(name = "email", unique = true)
    String email;
    String password;

    @ManyToOne
    @JoinColumn(name = "id_role")
    RoleEntity role;
}
