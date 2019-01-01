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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

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

        //return todoRepository.findByUsers_Id(user.getId());
        return todoRepository.findAllByUsers_IdOrderById(user.getId());
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
    public Stream<Todo> postUserTodos(@RequestBody Todo todo){
        User user = currentUserService.getUserFromRequest();
        if(user != null){
            user.getTodos().add(todo);
            User newUser = userRepository.save(user);
            return newUser.getTodos().stream().sorted((o1, o2) -> {return o1.getId() > o2.getId() ? 1 : -1;});
        }
        return null;
    }

    @PatchMapping("/todos")
    public Stream<Todo> patchUserTodos(@RequestBody Todo todo){
        User user = currentUserService.getUserFromRequest();
        if(user != null){
            Todo updateTodo = user.getTodos()
                    .stream()
                    .filter(todo1 -> todo1.getId() == todo.getId())
                    .findAny()
                    .orElse(null);
            //Optional<Todo> updateTodo = user.getTodos().stream().findFirst().filter(todo1 -> todo1.getId() == todo.getId());
            if(updateTodo != null){
                updateTodo.setDescription(todo.getDescription());
                updateTodo.setTitle(todo.getTitle());
                updateTodo.setDone(todo.isDone());
                User newUser = userRepository.save(user);
                return newUser.getTodos().stream().sorted((o1, o2) -> {return o1.getId() > o2.getId() ? 1 : -1;});
            }
        }
        return null;
    }

    @DeleteMapping("/todos/{todoid}")
    public List<Todo> deleteTodo(@PathVariable Long todoid){
        todoRepository.deleteById(todoid);
        return this.getUserTodos();
    }
}
