package io.github.ruan.springjpa.service;

import io.github.ruan.springjpa.dto.AuthorDTO;
import io.github.ruan.springjpa.dto.AuthorResponseDTO;
import io.github.ruan.springjpa.model.entitys.Author;
import io.github.ruan.springjpa.repository.AuthorRepository;
import io.github.ruan.springjpa.repository.BookRepository;
import io.github.ruan.springjpa.validation.AuthorValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorValidator authorValidator;
    private final BookRepository bookRepository;

    public UUID save(AuthorDTO author){
        Author savedToAuthor = author.parseToAuthor();
        authorValidator.validate(savedToAuthor);
        return authorRepository.save(savedToAuthor).getId();
    }

    public AuthorResponseDTO findById(String id){
        Optional<Author> author = authorRepository.findById(UUID.fromString(id));
         if(author.isPresent()){
             Author saved = author.get();
              return new AuthorResponseDTO(saved.getId(), saved.getName(), saved.getDateBirth(),
                     saved.getNationality());
         }
         return null;
    }

    public void deleteById(String id){
        authorRepository.deleteById(UUID.fromString(id));
    }

    public List<AuthorResponseDTO> findAllOrFilter(String name, String nationality){

        Author author = new Author();
        author.setName(name);
        author.setNationality(nationality);

        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withIgnorePaths("id", "dateBirth", "createTime", "updateTime")
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Author> example = Example.of(author, exampleMatcher);

        List<Author> list = authorRepository.findAll(example);

        return list.stream().map(authorResponse -> new AuthorResponseDTO(authorResponse.getId()
                , authorResponse.getName()
                , authorResponse.getDateBirth()
                , authorResponse.getNationality()
        )).collect(Collectors.toList());

    }

    public void replace(String id, AuthorDTO authorDTO){

        if (authorRepository.findById(UUID.fromString(id)).isEmpty()) {
            throw new IllegalArgumentException("author with is id not found");
        }

        Author author = authorDTO.parseToAuthor();
        authorValidator.validate(author);
        author.setId(UUID.fromString(id));
        authorRepository.save(author);
    }

    private List<AuthorResponseDTO> toAuthorResponseDTOList(List<Author> authors){
         return authors.stream().map(author -> new AuthorResponseDTO(author.getId(),
                author.getName(),
                author.getDateBirth(),
                author.getNationality())
        ).collect(Collectors.toList());
    }
}
