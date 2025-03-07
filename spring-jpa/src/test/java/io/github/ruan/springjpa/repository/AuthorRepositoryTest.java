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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@DisplayName("test in class author repository")
class AuthorRepositoryTest {
    @Autowired
    private AuthorRepository authorRepository;

    private UUID authorId;
    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("test return author saved")
    void save_PersistAuthor_WhenSucessuful() {
        Author author = new Author();
        author.setNationality("American");
        author.setName("Michael Phelps");
        author.setDate_birth(LocalDate.of(1978, 5, 23));

        var authorSaved = authorRepository.save(author);
        authorId = authorSaved.getId();
        System.out.println(authorSaved);
    }

    @Test
    @DisplayName("test in update author in database")
    void replace_UpdateAuthor_WhenSucessuful() {
        Optional<Author> authorOptional = authorRepository.findById(UUID.fromString("9228ee71-f152-40a7-b86e-d0ee4cab7a80"));
        if (authorOptional.isPresent()) {
            Author author = authorOptional.get();
            author.setNationality("EUA");
            var authorUpdate = authorRepository.save(author);
            System.out.println(authorUpdate);
        }
    }

    @Test
    @DisplayName("return list of author")
    void listAll_ListOfAuthor_WhenSucessuful() {
        List<Author> list = authorRepository.findAll();
        System.out.println(list);
    }

    @Test
    @DisplayName("return count of author by id")
    void count_ById_WhenSucessuful() {
        System.out.println(authorRepository.count());
    }

    @Test
    @DisplayName("delete by id author in data base")
    void deleteById_RemoveByIdAuthorInDataBase_WhenSucessuful() {
        authorRepository.deleteById(UUID.fromString("cb1077bf-8cd1-4f30-8532-f19366790e05"));
    }

    @Test
    @DisplayName("delete author in data base by object")
    void delete_ByAuthor_WhenSucessuful() {
        Optional<Author> author = authorRepository.findById(UUID.fromString("5913cfe8-34b9-48b2-bc9a-83e1ef67c834"));
        author.ifPresent(value -> authorRepository.delete(value));
    }

    @Test
    @DisplayName("test save author and relacionship without cascade")
    void save_PersistAuthorWithRelacionship_WhenSucessul() {
        Author author = new Author();
        author.setName("Hunter");
        author.setNationality("Brazilian");
        author.setDate_birth(LocalDate.of(2002, 7, 17));

        Book book = new Book();
        book.setGender(Gender.ACAO);
        book.setTitle("007");
        book.setIsBn("234343-adadad-232323");
        book.setValue(BigDecimal.valueOf(245.89));
        book.setDatePublication(LocalDate.of(2003, 2, 3));
        book.setAuthor(author);

        Book book2 = new Book();
        book2.setGender(Gender.ROMANCE);
        book2.setTitle("Romance 2");
        book2.setIsBn("ererere-34343-124");
        book2.setValue(BigDecimal.valueOf(12.89));
        book2.setDatePublication(LocalDate.of(2013, 9, 12));
        book2.setAuthor(author);


        ArrayList<Book> list = new ArrayList<>(List.of(book, book2));

        authorRepository.save(author);
        bookRepository.saveAll(list);
    }

    @Test
    @DisplayName("test save author and relacionship with cascade")
    void save_PersistAuthorWitchRelationship_WhenSucessuful() {
        Author author = new Author();
        author.setName("Donate");
        author.setNationality("African");
        author.setDate_birth(LocalDate.of(2005, 4, 24));
        author.setListBook(new ArrayList<>());

        Book book = new Book();
        book.setGender(Gender.AVENTURA);
        book.setTitle("RD2");
        book.setIsBn("332444-----0000000");
        book.setValue(BigDecimal.valueOf(156.99));
        book.setDatePublication(LocalDate.of(2010, 12, 30));
        book.setAuthor(author);

        Book book2 = new Book();
        book2.setGender(Gender.FICCAO);
        book2.setTitle("Game 2");
        book2.setIsBn("8400-3421");
        book2.setValue(BigDecimal.valueOf(1245.19));
        book2.setDatePublication(LocalDate.of(2020, 10, 22));
        book2.setAuthor(author);

        author.getListBook().addAll(List.of(book, book2));
        authorRepository.save(author);
    }

    @Test
    @DisplayName("test find boooks by author usaged query method")
    void findBookByAuthor_ReturnListWithBook_WhenSucessuful(){
        Author author = authorRepository.findAll().get(1);
        System.out.println(author);
        bookRepository.findByAuthor(author).forEach(System.out::println);
    }
}