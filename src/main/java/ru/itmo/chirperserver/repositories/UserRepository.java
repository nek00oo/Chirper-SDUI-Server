package ru.itmo.chirperserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.chirperserver.entities.ProfileUser;

public interface UserRepository extends JpaRepository<ProfileUser, Long> {
    ProfileUser findByUsername(String username);
}

