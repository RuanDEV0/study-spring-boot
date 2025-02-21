package io.github.ruan.springjpa;

import io.github.ruan.springjpa.model.entitys.Author;
import io.github.ruan.springjpa.repository.AuthorRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);
        AuthorRepository repository = run.getBean(AuthorRepository.class);
        saveAuthor(repository);
    }

    public static void saveAuthor(AuthorRepository authorRepository) {
        Author author = new Author();
        author.setName("Eduardo");
        author.setDate_birth(LocalDate.of(1975, 4, 23));
        author.setNationality("Venezuelano");

        Author saved = authorRepository.save(author);
        System.out.println("Author saved: " + saved);
    }

}
