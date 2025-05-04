package ru.itmo.chirperserver.models.styles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Layout {
    private String width;
    private String height;
    private Margin margin;
    private Padding padding;
    private String alignment;
}
