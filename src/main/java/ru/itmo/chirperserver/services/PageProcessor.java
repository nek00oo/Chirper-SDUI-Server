package ru.itmo.chirperserver.services;

import org.springframework.stereotype.Component;
import ru.itmo.chirperserver.models.PageResponse;
import ru.itmo.chirperserver.models.Screen;
import ru.itmo.chirperserver.models.UiComponent;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class PageProcessor {

    public PageResponse personalize(PageResponse template, Map<String,Object> userData) {
        List<UiComponent> comps = template.getScreen().getComponents().stream()
                .map(c -> personalizeComponent(c, userData))
                .collect(Collectors.toList());

        Screen screen = template.getScreen();
        screen.setComponents(comps);
        return template;
    }

    private UiComponent personalizeComponent(UiComponent component, Map<String, Object> userData) {
        if (component.getProps() != null) {
            component.getProps().forEach((key, value) -> {
                if (value instanceof String str && str.startsWith("{{") && str.endsWith("}}")) {
                    String placeholder = str.substring(2, str.length() - 2);
                    Object userValue = userData.get(placeholder);
                    if (userValue != null) {
                        component.getProps().put(key, userValue);
                    }
                }
            });
        }
        return component;
    }

}

