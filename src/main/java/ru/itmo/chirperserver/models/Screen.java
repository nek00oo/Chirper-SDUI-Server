package ru.itmo.chirperserver.models;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Value
@AllArgsConstructor
public class Screen {
    String title;
    List<UiComponent> components;

    public Screen withComponents(List<UiComponent> newComponents) {
        return new Screen(this.title, newComponents);
    }
}

