package io.github.ruan.springjpa.repository;

import io.github.ruan.springjpa.model.entitys.Author;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@DisplayName("test in class author repository")
class AuthorRepositoryTest {
    @Autowired
    private AuthorRepository authorRepository;

    private UUID authorId;

    @Test
    @DisplayName("test return author saved")
    void save_PersistAuthor_WhenSucessuful(){
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
    void replace_UpdateAuthor_WhenSucessuful(){
        Optional<Author> authorOptional = authorRepository.findById(UUID.fromString("9228ee71-f152-40a7-b86e-d0ee4cab7a80"));
        if(authorOptional.isPresent()){
            Author author = authorOptional.get();
            author.setNationality("EUA");
            var authorUpdate = authorRepository.save(author);
            System.out.println(authorUpdate);
        }
    }

    @Test
    @DisplayName("return list of author")
    void listAll_ListOfAuthor_WhenSucessuful(){
        List<Author> list = authorRepository.findAll();
        System.out.println(list);
    }

    @Test
    @DisplayName("return count of author by id")
    void count_ById_WhenSucessuful(){
        System.out.println(authorRepository.count());
    }

    @Test
    @DisplayName("delete by id author in data base")
    void deleteById_RemoveAuthorInDataBase_WhenSucessuful(){
        authorRepository.deleteById(UUID.fromString("ee7cf962-7342-4fb8-acc0-3759944fcd80"));
    }

    @Test
    @DisplayName("delete author in data base by object")
    void delete_ByAuthor_WhenSucessuful(){
        Optional<Author> author = authorRepository.findById(UUID.fromString("5913cfe8-34b9-48b2-bc9a-83e1ef67c834"));
        author.ifPresent(value -> authorRepository.delete(value));
    }
}