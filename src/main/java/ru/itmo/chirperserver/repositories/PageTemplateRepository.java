package ru.itmo.chirperserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.chirperserver.entities.PageTemplate;

public interface PageTemplateRepository extends JpaRepository<PageTemplate, String> {
}

