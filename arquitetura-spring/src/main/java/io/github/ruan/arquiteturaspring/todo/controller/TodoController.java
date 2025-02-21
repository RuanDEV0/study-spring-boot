package io.github.ruan.arquiteturaspring.todo.controller;

import io.github.ruan.arquiteturaspring.todo.entity.TodoEntity;
import io.github.ruan.arquiteturaspring.todo.service.TodoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todo")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public TodoEntity save(@RequestBody TodoEntity todo) {
        return todoService.save(todo);
    }

    @PutMapping("/{id}")
    public void replaceStatus(@PathVariable Integer id, @RequestBody TodoEntity todo) {
        todo.setId(id);
        todoService.replaceStatus(todo);
    }

    @GetMapping("/{id}")
    public TodoEntity findById(@PathVariable Integer id){
        return todoService.findById(id);
    }
}
