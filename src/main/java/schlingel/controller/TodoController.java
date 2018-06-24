package schlingel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import schlingel.domain.Todo;
import schlingel.repository.TodoRepository;

import java.util.List;

@RestController
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping("/users/{userid}/todos")
    public List<Todo> getUserTodos(@PathVariable Long userid){
        return todoRepository.findByUsers_Id(userid);
    }
}
