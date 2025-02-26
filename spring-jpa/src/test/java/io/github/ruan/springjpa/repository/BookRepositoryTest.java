package io.github.ruan.springjpa.repository;

import io.github.ruan.springjpa.model.entitys.Author;
import io.github.ruan.springjpa.model.entitys.Book;
import io.github.ruan.springjpa.model.enums.Gender;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
@DisplayName("tests in class author repository")
class BookRepositoryTest {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;


    @Test
    @DisplayName("test save author return he saved!")
    void save_PersistAuthor_NotCascade() {
        Book book = new Book();
        book.setGender(Gender.ROMANCE);
        book.setTitle("A bela e a fera");
        book.setValue(new BigDecimal("180.89"));
        book.setIsBn("True");
        book.setDate_publication(LocalDate.of(2019, 9, 23));


        Author author = new Author();
        author.setNationality("American");
        author.setName("Michael Phelps");
        author.setDate_birth(LocalDate.of(1978, 5, 23));
        Author saved = authorRepository.save(author);

        book.setAuthorId(author);

        Book savedBook = bookRepository.save(book);
        System.out.println(savedBook);
    }
    @Test
    @DisplayName("test save author return he saved!")
    void save_PersistAuthor_WithCascade() {
        Book book = new Book();
        book.setGender(Gender.ACAO);
        book.setTitle("Velozes e Furiosos");
        book.setValue(new BigDecimal("280.89"));
        book.setIsBn("232442-2232");
        book.setDate_publication(LocalDate.of(2015, 2, 21));


        Author author = new Author();
        author.setNationality("EUA");
        author.setName("Michael Jordan");
        author.setDate_birth(LocalDate.of(1970, 1, 14));

        book.setAuthorId(author);

        Book savedBook = bookRepository.save(book);
        System.out.println(savedBook);
    }
}