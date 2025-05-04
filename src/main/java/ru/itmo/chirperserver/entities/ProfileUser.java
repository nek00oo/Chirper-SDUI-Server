package ru.itmo.chirperserver.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ProfileUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;

    private String firstname;
    private String lastname;
    private String avatarUrl;
    private String city;
    private String education;
    private String experience;
    private Integer postCount;
    private Integer followersCount;
}
