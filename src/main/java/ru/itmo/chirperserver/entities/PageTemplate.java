package ru.itmo.chirperserver.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;

@Entity
@Data
public class PageTemplate {
    @Id
    private String id;
    @Lob
    private String json;
}

