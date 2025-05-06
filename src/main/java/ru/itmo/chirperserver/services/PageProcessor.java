package ru.itmo.chirperserver.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.itmo.chirperserver.models.PageResponse;
import ru.itmo.chirperserver.models.Screen;
import ru.itmo.chirperserver.models.UiComponent;
import ru.itmo.chirperserver.utils.PlaceholderUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Component
public class PageProcessor {
    private static final Logger log = LoggerFactory.getLogger(PageProcessor.class);

    public PageResponse personalize(PageResponse template, Map<String, Object> userData) {
        Screen personalizedScreen = processScreen(template.screen(), userData);

        return new PageResponse(personalizedScreen);
    }

    private Screen processScreen(Screen screen, Map<String, Object> userData) {
        return screen.withComponents(
                screen.getComponents().stream()
                        .map(component -> processComponent(component, userData))
                        .toList()
        );
    }

    private UiComponent processComponent(UiComponent component, Map<String, Object> userData) {
        return component.withProps(
                processMap(component.getProps(), userData)
        );
    }

    private Map<String, Object> processMap(Map<String, Object> map, Map<String, Object> userData) {
        return map.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> processValue(e.getValue(), userData)
                ));
    }

    private Object processValue(Object value, Map<String, Object> userData) {
        if (value instanceof String str) {
            return processString(str, userData);
        }
        if (value instanceof Map<?, ?> map) {
            return processGenericMap(map, userData);
        }
        if (value instanceof List<?> list) {
            return processGenericList(list, userData);
        }
        return value;
    }

    private Map<String, Object> processGenericMap(Map<?, ?> rawMap, Map<String, Object> userData) {
        return rawMap.entrySet().stream()
                .collect(Collectors.toMap(
                        e -> e.getKey().toString(),
                        e -> processValue(e.getValue(), userData)
                ));
    }

    private List<Object> processGenericList(List<?> list, Map<String, Object> userData) {
        return list.stream()
                .map(item -> processValue(item, userData))
                .toList();
    }

    private Object processString(String value, Map<String, Object> userData) {
        if (!PlaceholderUtils.isPlaceholder(value)) return value;

        String key = PlaceholderUtils.extractKey(value);
        Object replacement = userData.getOrDefault(key, value);

        if (replacement == value) {
            log.warn("Placeholder '{}' not found in user data", key);
        }

        return replacement;
    }
}


