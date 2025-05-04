package ru.itmo.chirperserver.models.styles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Style {
    private String backgroundColor;
    private Integer cornerRadius;
}
