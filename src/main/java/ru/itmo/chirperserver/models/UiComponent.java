package ru.itmo.chirperserver.models;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.itmo.chirperserver.models.styles.Layout;
import ru.itmo.chirperserver.models.styles.Style;

import java.util.Map;

@Document
@Value
@AllArgsConstructor
public class UiComponent {
    String type;
    Layout layout;
    Style style;
    Map<String, Object> props;

    public UiComponent withProps(Map<String, Object> newProps) {
        return new UiComponent(this.type, this.layout, this.style, newProps);
    }
}

