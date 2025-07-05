package ToDoApp3.demo.Controller;

import ToDoApp3.demo.Model.Todo;
import ToDoApp3.demo.Model.User;
import ToDoApp3.demo.Repository.TodoRepo;
import ToDoApp3.demo.Repository.UserRepo;
import ToDoApp3.demo.Service.TodoService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TodoController {

    private TodoService service;
    private TodoRepo todoRepo;
    private UserRepo userRepo;

    public TodoController(TodoService service, TodoRepo todoRepo,UserRepo userRepo) {
        this.service = service;
        this.todoRepo = todoRepo;
        this.userRepo = userRepo;

    }


    @GetMapping("/alltodos")
    public List<Todo> getAll () {
       return service.getAll();

    }

    @GetMapping("/usertodos")
    public List<Todo> getUserTodos(Authentication authentication) {
        String username = authentication.getName();
        User user = userRepo.findByUsername(username);
        return service.getUserTodos(user);
    }
}

