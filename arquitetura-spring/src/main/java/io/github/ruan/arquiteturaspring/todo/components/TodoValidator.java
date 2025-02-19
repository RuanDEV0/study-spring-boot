package io.github.ruan.arquiteturaspring.todo.components;

import io.github.ruan.arquiteturaspring.todo.repository.TodoRepository;
import org.springframework.stereotype.Component;

@Component
public class TodoValidator {
    private TodoRepository todoRepository;

    public TodoValidator(TodoRepository todoRepository){
        this.todoRepository = todoRepository;
    }
}
