package io.github.ruan.arquiteturaspring.todo.service;

import io.github.ruan.arquiteturaspring.todo.components.MailSender;
import io.github.ruan.arquiteturaspring.todo.components.TodoValidator;
import io.github.ruan.arquiteturaspring.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
    private TodoRepository todoRepository;
    private TodoValidator todoValidator;
    private MailSender mailSender;

    public TodoService(TodoRepository todoRepository,
                       TodoValidator todoValidator,
                       MailSender mailSender) {
        this.todoValidator = todoValidator;
        this.mailSender = mailSender;
        this.todoRepository = todoRepository;
    }
}
