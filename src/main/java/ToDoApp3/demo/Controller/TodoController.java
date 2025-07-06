package ToDoApp3.demo.Controller;

import ToDoApp3.demo.Dto.TodoRequest;
import ToDoApp3.demo.Model.Todo;
import ToDoApp3.demo.Model.User;
import ToDoApp3.demo.Repository.TodoRepo;
import ToDoApp3.demo.Repository.UserRepo;
import ToDoApp3.demo.Service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usertodos")
public class TodoController {

    private TodoService service;
    private TodoRepo todoRepo;
    private UserRepo userRepo;

    public TodoController(TodoService service, TodoRepo todoRepo, UserRepo userRepo) {
        this.service = service;
        this.todoRepo = todoRepo;
        this.userRepo = userRepo;

    }

    @GetMapping("/{id}")
    public Todo getTodo(@PathVariable Long id) {
        return service.getUserTodoById(id);
    }


    @GetMapping()
    public List<Todo> getUserTodos(Authentication authentication) {
        String username = authentication.getName();
        User user = userRepo.findByUsername(username);
        return service.getUserTodos(user);
    }

    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody TodoRequest todoRequest) {
        Todo savedTodo = service.createTodo(todoRequest);
        return ResponseEntity.ok(savedTodo);
    }

    @PatchMapping("{todoId}/complete")
    public Todo markTodoAsCompleted(@PathVariable Long todoId, Authentication authentication) {
        return service.markTodoAsCompleted(todoId, authentication.getName());
    }
}