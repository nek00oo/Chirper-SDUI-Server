package ru.itmo.chirperserver.services;

import org.springframework.stereotype.Service;
import ru.itmo.chirperserver.models.PageResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.itmo.chirperserver.entities.PageTemplate;
import ru.itmo.chirperserver.repositories.PageTemplateRepository;

import java.util.Map;
import java.util.Optional;

@Service
public class PageService {
    private final RedisService redisService;
    private final UserService userService;
    private final PageTemplateRepository templateRepo;
    private final PageProcessor pageProcessor;
    private final ObjectMapper objectMapper;

    public PageService(RedisService redisService,
                       UserService userService,
                       PageTemplateRepository templateRepo,
                       PageProcessor pageProcessor,
                       ObjectMapper objectMapper) {
        this.redisService   = redisService;
        this.userService    = userService;
        this.templateRepo   = templateRepo;
        this.pageProcessor  = pageProcessor;
        this.objectMapper   = objectMapper;
    }

    public PageResponse getPage(String pageId, Long userId) {
        Optional<String> optJson = redisService.getTemplateJson(pageId);
        String templateJson = optJson.orElseGet(() -> {
            PageTemplate tpl = templateRepo.findById(pageId)
                    .orElseThrow(() -> new RuntimeException("Page not found: " + pageId));

            String json = tpl.getJson();
            redisService.cacheTemplateJson(pageId, json);

            return json;
        });

        PageResponse template;
        try {
            template = objectMapper.readValue(templateJson, PageResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse template JSON", e);
        }

        Map<String,Object> userData = userService.loadUserData(userId, pageId);

        return pageProcessor.personalize(template, userData);
    }
}


