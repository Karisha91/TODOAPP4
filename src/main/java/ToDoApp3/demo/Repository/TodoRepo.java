package ToDoApp3.demo.Repository;

import ToDoApp3.demo.Model.Todo;
import ToDoApp3.demo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepo extends JpaRepository<Todo, Long> {
    List<Todo> findByUser(User user);
}
