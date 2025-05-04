package ru.itmo.chirperserver.exceptions;

public class PageNotFoundException extends RuntimeException {
    public PageNotFoundException(String name) {
        super("Page not found: " + name);
    }
}
