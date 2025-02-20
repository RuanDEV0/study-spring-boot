package io.github.ruan.arquiteturaspring.todo.components;

import io.github.ruan.arquiteturaspring.todo.entity.TodoEntity;
import io.github.ruan.arquiteturaspring.todo.repository.TodoRepository;
import org.springframework.stereotype.Component;

@Component
public class TodoValidator {
    private final TodoRepository todoRepository;

    public TodoValidator(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public void validator(TodoEntity todoEntity) {
        if(existsTodoByDescription(todoEntity.getDescription())){
            throw new IllegalArgumentException("TodoEntity exists!");
        }

    }

    private boolean existsTodoByDescription(String description){
        return todoRepository.existsByDescription(description);
    }
}
