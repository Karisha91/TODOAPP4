package ToDoApp3.demo.Service;

import ToDoApp3.demo.Model.Todo;
import ToDoApp3.demo.Model.User;
import ToDoApp3.demo.Repository.TodoRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    private TodoRepo todoRepo;

    public TodoService(TodoRepo todoRepo) {
        this.todoRepo = todoRepo;
    }

    public List<Todo> getAll() {
        return todoRepo.findAll();
    }

    public List<Todo> getUserTodos(User user) {
        return todoRepo.findByUser(user);
    }
}
