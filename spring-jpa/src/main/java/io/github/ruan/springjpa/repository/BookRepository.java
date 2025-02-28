package io.github.ruan.springjpa.repository;

import io.github.ruan.springjpa.model.entitys.Author;
import io.github.ruan.springjpa.model.entitys.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
    List<Book> findByAuthor(Author author);
}
