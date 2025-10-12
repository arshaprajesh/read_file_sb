package com.arsha.code.userFile.userService;



import com.arsha.code.userFile.dto.UserRequest;
import com.arsha.code.userFile.model.FileRecord;
import com.arsha.code.userFile.model.User;
import com.arsha.code.userFile.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public Map<String, Object> createUser(UserRequest data) throws IOException {
        System.out.println("starting user service");
        String basePath = "/Users/kunip004/Documents/arsha/docs/project";
        Path fullPath = Paths.get(basePath, Paths.get(data.filePath).getFileName().toString());
        System.out.println("fullPath"+fullPath);
        String description = Files.readString(fullPath);
        System.out.println("description"+description);

        User user = new User();
        user.setName(data.userName);
        System.out.println("user"+user);
        user.setDate(LocalDate.parse(data.userDate));
        user.setState(data.userState);
        user.setCreatedBy("arsha");
        user.setCreatedAt(LocalDate.of(2025, 10, 2));
        System.out.println("user"+user);

        FileRecord file = new FileRecord();
        file.setTitle(data.filePath);
        file.setDescription(description);
        file.setUser(user);
        System.out.println("file"+file);

        user.getFiles().add(file);
        userRepo.save(user);
        System.out.println("userRepo"+userRepo);

        return Map.of(
                "user", Map.of("name", user.getName(), "date", user.getDate(), "state", user.getState()),
                "file", Map.of("title", file.getTitle(), "description", file.getDescription())
        );
    }

    public ResponseEntity<?> getUser(@PathVariable Long userId) {
        System.out.println("starting getting user");
        return userRepo.findById(userId)
                .map(user -> ResponseEntity.ok(Map.of(
                        "userID", user.getUserID(),
                        "name", user.getName(),
                        "date", user.getDate(),
                        "state", user.getState()
                )))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "User not found")));
    }



    public List<Map<String, Object>> searchByName(String name) {
        System.out.println("searching getting user");
        List<User> users = userRepo.findByName(name);
        return users.stream().map(user -> Map.of(
                "userID", user.getUserID(),
                "name", user.getName(),
                "date", user.getDate(),
                "state", user.getState(),
                "files", user.getFiles().stream().map(f -> Map.of(
                        "title", f.getTitle(),
                        "description", f.getDescription()
                )).collect(Collectors.toList())
        )).collect(Collectors.toList());
    }
}
