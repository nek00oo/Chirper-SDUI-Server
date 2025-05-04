package ru.itmo.chirperserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.itmo.chirperserver.entities.ProfileUser;
import ru.itmo.chirperserver.services.UserService;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")
    public ProfileUser getUser(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @PostMapping
    public ProfileUser createUser(@RequestBody ProfileUser profileUser) {
        return userService.createUser(profileUser);
    }
}

