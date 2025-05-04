package ru.itmo.chirperserver.models;

import lombok.Data;
import java.util.List;

@Data
public class Screen {
    private String title;
    private List<UiComponent> components;
}

