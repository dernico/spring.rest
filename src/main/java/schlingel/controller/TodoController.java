package schlingel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import schlingel.domain.Todo;
import schlingel.domain.User;
import schlingel.repository.TodoRepository;
import schlingel.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class TodoController {

    private TodoRepository todoRepository;
    private UserRepository userRepository;

    public TodoController(TodoRepository todoRepository, UserRepository userRepository){
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/users/{userid}/todos")
    public List<Todo> getUserTodos(@PathVariable Long userid){
        return todoRepository.findByUsers_Id(userid);
//        Set<Todo> todos = userRepository.findById(userid).get().getTodos();
//        return todos;
    }

    @PostMapping("/users/{userid}/todos")
    public User postUserTodos(@PathVariable Long userid, @RequestBody Todo todo){
        Optional<User> user = userRepository.findById(userid);
        if(user.isPresent()){
//            todo.getUsers().add(user.get());
//            Todo newTodo = todoRepository.save(todo);
            user.get().getTodos().add(todo);
            User newUser = userRepository.save(user.get());
            return newUser;
        }
        return null;
    }
}
