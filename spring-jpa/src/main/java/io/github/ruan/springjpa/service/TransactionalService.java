package io.github.ruan.springjpa.service;

import io.github.ruan.springjpa.model.entitys.Author;
import io.github.ruan.springjpa.model.entitys.Book;
import io.github.ruan.springjpa.model.enums.Gender;
import io.github.ruan.springjpa.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class TransactionalService {

    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public void insertAuthorAndBookWithCommit() {
        Book book = new Book();
        book.setGender(Gender.COMEDIA);
        book.setTitle("33 Taticas de Guerra");
        book.setValue(BigDecimal.valueOf(223.45));
        book.setIsBn("299331-@43433");
        book.setDatePublication(LocalDate.of(2012, 8, 11));


        Author author = new Author();
        author.setNationality("EUA");
        author.setName("Michael Jordan");
        author.setDate_birth(LocalDate.of(1973, 5, 20));

        book.setAuthor(author);

        bookRepository.save(book);
    }


    @Transactional
    public void insertAuthorAndBookWithRollback() {
        Book book = new Book();
        book.setGender(Gender.DRAMA);
        book.setTitle("TESTANDO O 33 Taticas de Guerra");
        book.setValue(BigDecimal.valueOf(223.45));
        book.setIsBn("@43433");
        book.setDatePublication(LocalDate.of(2012, 8, 11));


        Author author = new Author();
        author.setNationality("EUA");
        author.setName("Michael Phelps");
        author.setDate_birth(LocalDate.of(1973, 5, 20));

        book.setAuthor(author);

        bookRepository.save(book);


        if (author.getName().equals("Michael Phelps")) {
            throw new RuntimeException("Erro gerando rollback");
        }
    }

    @Transactional
    public void updateBookWithoutMethodSave() {
        var book = bookRepository.findById(
                        UUID.fromString("f278b622-ce62-4db7-8e35-4b71c42b02f0"))
                .orElse(null);

        book.setDatePublication(LocalDate.now());
    }
}
