package schlingel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import schlingel.domain.Person;
import schlingel.domain.Todo;
import schlingel.repository.ICustomPersonSave;
import schlingel.repository.PersonRepository;
import schlingel.repository.TodoRepository;

public class CustomPersonSave implements ICustomPersonSave<Person> {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    TodoRepository todoRepository;

    @Override
    public <S extends Person> S save(S entity) {

        entity.getTodos().forEach(todo -> {

            Todo newTodo = todoRepository.save(todo);

            todo.setId(newTodo.getId());
        });

        return null;
    }
}
