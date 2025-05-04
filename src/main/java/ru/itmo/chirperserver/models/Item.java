package ru.itmo.chirperserver.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Item {
    private String label;
    private String value;
}

