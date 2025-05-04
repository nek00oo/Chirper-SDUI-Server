package ru.itmo.chirperserver.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
}


