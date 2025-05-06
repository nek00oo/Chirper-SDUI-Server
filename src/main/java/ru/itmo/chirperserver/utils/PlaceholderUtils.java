package ru.itmo.chirperserver.utils;

public class PlaceholderUtils {

    private static final String PREFIX = "{{";
    private static final String SUFFIX = "}}";

    public static boolean isPlaceholder(String value) {
        return value != null && value.startsWith(PREFIX) && value.endsWith(SUFFIX);
    }

    public static String extractKey(String placeholder) {
        if (!isPlaceholder(placeholder)) {
            return placeholder;
        }
        return placeholder.substring(PREFIX.length(), placeholder.length() - SUFFIX.length()).trim();
    }
}
