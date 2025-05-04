package ru.itmo.chirperserver.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.itmo.chirperserver.entities.PageTemplate;

import java.util.Optional;

public interface PageTemplateRepository extends MongoRepository<PageTemplate, String> {
    Optional<PageTemplate> findByName(String name);
}
