package ru.itmo.chirperserver.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "pages")
public class UserDataFieldsConfig {
    private Map<String, List<String>> pages;
}
