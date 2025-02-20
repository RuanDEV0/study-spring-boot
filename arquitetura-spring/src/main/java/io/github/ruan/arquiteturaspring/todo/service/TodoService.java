package io.github.ruan.arquiteturaspring.todo.service;

import io.github.ruan.arquiteturaspring.todo.components.MailSender;
import io.github.ruan.arquiteturaspring.todo.components.TodoValidator;
import io.github.ruan.arquiteturaspring.todo.entity.TodoEntity;
import io.github.ruan.arquiteturaspring.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
    private final TodoRepository todoRepository;
    private final TodoValidator todoValidator;
    private final MailSender mailSender;

    public TodoService(TodoRepository todoRepository,
                       TodoValidator todoValidator,
                       MailSender mailSender) {
        this.todoValidator = todoValidator;
        this.mailSender = mailSender;
        this.todoRepository = todoRepository;
    }

    public TodoEntity save(TodoEntity todo) {
        todoValidator.validator(todo);
        return todoRepository.save(todo);
    }

    public void replaceStatus(TodoEntity todo) {
        todoRepository.save(todo);
        String isCompleted = todo.getCompleted() == Boolean.TRUE ? "Concluido" : "Nao concluido";
        mailSender.sender("Todo " + todo.getDescription() + " foi atualiado para o status " + todo.getCompleted());
    }

    public TodoEntity findById(Integer id){
        return todoRepository.findById(id).orElse(null);
    }
}
