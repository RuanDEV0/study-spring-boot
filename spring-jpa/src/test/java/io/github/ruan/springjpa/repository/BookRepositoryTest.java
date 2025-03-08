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
import java.util.List;
import java.util.UUID;

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
        book.setDatePublication(LocalDate.of(2019, 9, 23));


        Author author = new Author();
        author.setNationality("American");
        author.setName("Michael Phelps");
        author.setDate_birth(LocalDate.of(1978, 5, 23));
        Author saved = authorRepository.save(author);

        book.setAuthor(author);

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
        book.setDatePublication(LocalDate.of(2015, 2, 21));


        Author author = new Author();
        author.setNationality("EUA");
        author.setName("Michael Jordan");
        author.setDate_birth(LocalDate.of(1970, 1, 14));

        book.setAuthor(author);

        Book savedBook = bookRepository.save(book);
        System.out.println(savedBook);
    }

    @DisplayName("test replace author of book")
    @Test
    void replace_PersistAuthorOfBook_WhenSucessuful() {
        Book book = bookRepository.findById(UUID.fromString("37dde338-3321-4912-b9ce-b16e79b574cc"))
                .orElse(null);

        Author author = authorRepository.findById(UUID.fromString("58e295ec-cc0b-4570-b32c-abb734b0fc3c"))
                .orElse(null);


        book.setAuthor(author);
        bookRepository.save(book);
    }

    @Test
    @DisplayName("test delete book in data base for with cascade or not")
    void deleteById_RemoveInDataBase_WhenSucessuful() {
        bookRepository.deleteById(UUID.fromString("37dde338-3321-4912-b9ce-b16e79b574cc"));
        System.out.println(bookRepository.findAll());
    }

    @Test
    @DisplayName("return book by title")
    void findByTitle() {
        Book book = bookRepository.findByTitle("Romance 2");
        System.out.println(book);
    }

    @Test
    @DisplayName("return book by isBn")
    void findByIsbn() {
        Book book = bookRepository.findByIsBn("ererere-34343-124");
        System.out.println(book);
    }

    @Test
    @DisplayName("return book by title and value")
    void findByTitleAndValue() {
        Book bookByTitleAndValue = bookRepository.findByTitleAndValue("007", BigDecimal.valueOf(245.89));
        System.out.println(bookByTitleAndValue);
    }

    @Test
    @DisplayName("retur list of book by title or isBn")
    void findByTitleOrIsbn() {
        List<Book> bookList = bookRepository.findByTitleOrIsBn("RD2", "ererere-34343-124");
        bookList.forEach(System.out::println);
    }

    @Test
    @DisplayName("listed book order by title and value using jpql query")
    void listedBookOrderByTitleAndValue() {
        List<Book> books = bookRepository.listedBookOrderByTitleAndValue();
        books.forEach(System.out::println);
    }

    @Test
    @DisplayName("listed author by books using jpql query")
    void listedAuthorOfBooks() {
        List<Author> books = bookRepository.listedAuthorOfBook();
        books.forEach(System.out::println);
    }

    @Test
    @DisplayName("listed title unique of books using jpql query")
    void listedTitleUniqueOfBooks() {
        List<String> books = bookRepository.listedTitleUniqueOfBook();
        books.forEach(System.out::println);
    }

    @Test
    @DisplayName("listed gender of books where author is brazilian using jpql query")
    void listedByGenderOfBookWhereAuthorIsBrazilian() {
        List<String> books = bookRepository.listedByGenderOfBookWhereAuthorIsBrazilian();
        books.forEach(System.out::println);
    }
}