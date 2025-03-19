package io.github.ruan.springjpa.validation;

import io.github.ruan.springjpa.exception.RegisterDuplicateException;
import io.github.ruan.springjpa.model.entitys.Author;
import io.github.ruan.springjpa.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthorValidator{
    private final AuthorRepository authorRepository;

    public void validate(Author author) {
        if(exictsAuthor(author)){
            throw new RegisterDuplicateException("author already exists");
        }
    }

    private boolean exictsAuthor(Author author) {
        Optional<Author> authorSaved = authorRepository.findByNameAndDateBirthAndNationality(author.getName(),
                author.getDateBirth(),
                author.getNationality());

        if(author.getId() == null){
            return authorSaved.isPresent();
        }

        return !author.getId().equals(authorSaved.get().getId()) && authorSaved.isPresent();
    }
}
