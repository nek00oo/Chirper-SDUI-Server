package ru.itmo.chirperserver.entities;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.itmo.chirperserver.models.Screen;

@Document(collection = "pages")
@Data
public class PageTemplate {
    @Id
    private String id;

    private String name;

    private Screen screen;
}

