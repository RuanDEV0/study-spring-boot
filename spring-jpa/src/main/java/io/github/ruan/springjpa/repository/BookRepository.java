package io.github.ruan.springjpa.repository;

import io.github.ruan.springjpa.model.entitys.Author;
import io.github.ruan.springjpa.model.entitys.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
    List<Book> findByAuthor(Author author);

    Book findByTitle(String title);

    Book findByIsBn(String isBn);

    Book findByTitleAndValue(String title, BigDecimal value);

    List<Book> findByTitleOrIsBn(String title, String isBn);

    List<Book> findByDatePublicationBetween(LocalDate start, LocalDate last);
}
