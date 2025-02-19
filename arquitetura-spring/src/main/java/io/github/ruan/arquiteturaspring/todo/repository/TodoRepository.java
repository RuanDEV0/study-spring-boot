package io.github.ruan.arquiteturaspring.todo.repository;

import io.github.ruan.arquiteturaspring.todo.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TodoEntity, Integer> {
    Boolean existsByDescription(String description);
}
