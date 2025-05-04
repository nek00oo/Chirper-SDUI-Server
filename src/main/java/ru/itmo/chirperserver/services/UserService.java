package ru.itmo.chirperserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.chirperserver.configs.UserDataFieldsConfig;
import ru.itmo.chirperserver.entities.User;
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

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Map<String, Object> loadUserData(Long userId, String pageId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User with id " + userId + " not found"));
        List<String> fields = userDataFieldsConfig.getPages().get(pageId);

        Map<String, Object> result = new HashMap<>();
        for (String field : fields) {
            try {
                var value = User.class.getDeclaredField(field);
                value.setAccessible(true);
                result.put(field, value.get(user));
            } catch (NoSuchFieldException | IllegalAccessException e) {
                System.err.println("Can't extract field: " + field);
            }
        }
        return result;
    }

}

