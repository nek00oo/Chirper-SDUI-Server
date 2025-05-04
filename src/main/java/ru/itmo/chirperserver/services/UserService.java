package ru.itmo.chirperserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.chirperserver.configs.UserDataFieldsConfig;
import ru.itmo.chirperserver.entities.ProfileUser;
import ru.itmo.chirperserver.repositories.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserDataFieldsConfig userDataFieldsConfig;

    @Autowired
    public UserService(UserRepository userRepository, UserDataFieldsConfig userDataFieldsConfig) {
        this.userRepository = userRepository;
        this.userDataFieldsConfig = userDataFieldsConfig;
    }

    public ProfileUser getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public ProfileUser createUser(ProfileUser profileUser) {
        return userRepository.save(profileUser);
    }

    public Map<String, Object> loadUserData(Long userId, String pageId) {
        ProfileUser profileUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User with id " + userId + " not found"));
        Map<String, List<String>> pagesConfig = userDataFieldsConfig.getPages();

        if (pagesConfig == null || pagesConfig.isEmpty()) {
            throw new IllegalStateException("Pages configuration is missing");
        }

        List<String> fields = pagesConfig.get(pageId);

        Map<String, Object> result = new HashMap<>();
        for (String field : fields) {
            try {
                var value = ProfileUser.class.getDeclaredField(field);
                value.setAccessible(true);
                result.put(field, value.get(profileUser));
            } catch (NoSuchFieldException | IllegalAccessException e) {
                System.err.println("Can't extract field: " + field);
            }
        }
        return result;
    }

}

