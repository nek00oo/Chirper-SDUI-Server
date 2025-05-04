package ru.itmo.chirperserver.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.chirperserver.models.PageResponse;
import ru.itmo.chirperserver.services.PageService;

@RestController
@RequestMapping("/api/v1/pages")
public class PageController {
    private final PageService pageService;

    public PageController(PageService pageService) {
        this.pageService = pageService;
    }

    @GetMapping("/{pageName}")
    public ResponseEntity<PageResponse> getPage(@PathVariable String pageName, @RequestParam Long userId) {
        PageResponse page = pageService.getPage(pageName, userId);
        return ResponseEntity.ok(page);
    }
}

