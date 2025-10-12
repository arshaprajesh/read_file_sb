

package com.arsha.code.userFile.controller;




        import com.arsha.code.userFile.dto.UserRequest;
        import com.arsha.code.userFile.userService.UserService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;

        import java.io.IOException;
        import java.util.List;
        import java.util.Map;


@CrossOrigin(origins = "http://localhost:5173")//because springboot and react running in different port
@RestController
@RequestMapping("/userDetails")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/userFile")
    public ResponseEntity<?> createUser(@RequestBody UserRequest data) {
        System.out.println("post mapping starting");
        try {
            Map<String, Object> result = userService.createUser(data);
            System.out.println("result"+result);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "File not found"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/userFile/{userId}")
    public ResponseEntity<?> getUser(@PathVariable Long userId) {
        System.out.println("get mapping starting");
        Map<String, Object> result = (Map<String, Object>) userService.getUser(userId);
        System.out.println("result"+result);
        if (result == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "User not found"));
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/userFile/search")
    public ResponseEntity<?> searchByName(@RequestParam String name) {
        System.out.println("search mapping starting");
        List<Map<String, Object>> result = userService.searchByName(name);
        System.out.println("result"+result);
        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "No users found"));
        }
        return ResponseEntity.ok(result);
    }
}
