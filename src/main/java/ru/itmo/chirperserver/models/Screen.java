package ru.itmo.chirperserver.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
public class Screen {
    private String title;
    private List<UiComponent> components;
}

