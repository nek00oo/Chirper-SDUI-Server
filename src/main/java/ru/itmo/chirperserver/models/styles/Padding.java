package ru.itmo.chirperserver.models.styles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Padding {
    private Integer top;
    private Integer bottom;
    private Integer left;
    private Integer right;
    private Integer all;
}