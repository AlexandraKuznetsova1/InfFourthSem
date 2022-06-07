package com.itis.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    private String email;

    private String firstName;

    private String lastName;

    private String hashedPassword;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    public enum Role {
        ADMIN, USER;
    }
}
