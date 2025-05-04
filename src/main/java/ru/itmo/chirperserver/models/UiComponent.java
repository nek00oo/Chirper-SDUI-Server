package ru.itmo.chirperserver.models;

import lombok.Data;
import ru.itmo.chirperserver.models.styles.Layout;
import ru.itmo.chirperserver.models.styles.Style;

import java.util.List;
import java.util.Map;

@Data
public class UiComponent {
    private String type;
    private String text;
    private String url;
    private Style style;
    private Layout layout;
    private List<Item> items;
    private Map<String, Object> props;
}

