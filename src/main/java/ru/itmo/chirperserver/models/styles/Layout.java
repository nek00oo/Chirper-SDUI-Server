package ru.itmo.chirperserver.models.styles;

import lombok.Data;

@Data
public class Layout {
    private String width;
    private String height;
    private Margin margin;
    private Padding padding;
    private String alignment;
}
