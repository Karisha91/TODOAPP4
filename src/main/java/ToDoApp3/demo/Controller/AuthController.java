package ToDoApp3.demo.Controller;

import ToDoApp3.demo.Dto.RegistrationDto;
import ToDoApp3.demo.Model.User;
import ToDoApp3.demo.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegistrationDto registrationDto) {
        User newUser = userService.registerNewUser(registrationDto);
        return ResponseEntity.ok(newUser);
    }
}