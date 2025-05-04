package ru.itmo.chirperserver.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RedisService {
    private final StringRedisTemplate redis;

    public Optional<String> getTemplateJson(String pageId) {
        String key = "page:template:" + pageId;
        return Optional.ofNullable(redis.opsForValue().get(key));
    }

    public void cacheTemplateJson(String pageId, String json) {
        String key = "page:template:" + pageId;
        redis.opsForValue().set(key, json, Duration.ofHours(1));
    }
}


