package ToDoApp3.demo.Service;

import ToDoApp3.demo.Dto.TodoRequest;
import ToDoApp3.demo.Model.Todo;
import ToDoApp3.demo.Model.User;
import ToDoApp3.demo.Repository.TodoRepo;
import ToDoApp3.demo.Repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TodoService {


    private TodoRepo todoRepo;
    private UserRepo userRepo;

    public TodoService(TodoRepo todoRepo, UserRepo userRepo) {
        this.todoRepo = todoRepo;
        this.userRepo = userRepo;
    }

    public List<Todo> getAll() {
        return todoRepo.findAll();
    }

    public List<Todo> getUserTodos(User user) {
        return todoRepo.findByUser(user);
    }

    public Todo getUserTodoById(Long id) {
        return todoRepo.findByIdAndUser(id, getCurrentUser());
    }

    private User getCurrentUser() {
        String username = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        return userRepo.findByUsername(username);

    }

    @Transactional
    public Todo createTodo(TodoRequest request) {
        User user = userRepo.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + request.getUserId() + " not found"));
        Todo todo = new Todo();
        todo.setTitle(request.getTitle());
        todo.setDescription(request.getDescription());
        todo.setCompleted(request.isCompleted());
        todo.setDueDate(request.getDueDate());
        todo.setUser(user);  // Critical: Link the user
        return todoRepo.save(todo);

    }

    public Todo markTodoAsCompleted(Long todoId, String username) {
        User user = userRepo.findByUsername(username);
        Todo todo = todoRepo.findById(todoId)
                .orElseThrow(() -> new IllegalArgumentException("Todo not found"));

        if (!todo.getUser().getId().equals(user.getId())) {
            throw new IllegalStateException("Not authorized to complete this todo");
        }

        todo.setCompleted(true);
        return todoRepo.save(todo);
    }
}
