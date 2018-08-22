package schlingel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import schlingel.domain.Todo;
import schlingel.domain.User;
import schlingel.repository.TodoRepository;
import schlingel.repository.UserRepository;
import schlingel.services.ICurrentUserService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class TodoController {

    private TodoRepository todoRepository;
    private UserRepository userRepository;
    private ICurrentUserService currentUserService;

    public TodoController(TodoRepository todoRepository, UserRepository userRepository, ICurrentUserService currentUserService){
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
        this.currentUserService = currentUserService;
    }

    @GetMapping("/todos")
    public List<Todo> getUserTodos(){
        User user = currentUserService.getUserFromRequest();

        return todoRepository.findByUsers_Id(user.getId());
//        Set<Todo> todos = userRepository.findById(userid).get().getTodos();
//        return todos;
    }

    @GetMapping("/users/{userid}/todos")
    public List<Todo> getUserTodos2(@PathVariable Long userid){
        return todoRepository.findByUsers_Id(userid);
//        Set<Todo> todos = userRepository.findById(userid).get().getTodos();
//        return todos;
    }

    @PostMapping("/todos")
    public Set<Todo> postUserTodos(@RequestBody Todo todo){
        User user = currentUserService.getUserFromRequest();
        if(user != null){
            user.getTodos().add(todo);
            User newUser = userRepository.save(user);
            return newUser.getTodos();
        }
        return null;
    }

    @DeleteMapping("/todos/{todoid}")
    public List<Todo> deleteTodo(@PathVariable Long todoid){
        todoRepository.deleteById(todoid);
        return this.getUserTodos();
    }
}
