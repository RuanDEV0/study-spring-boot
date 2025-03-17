package io.github.ruan.springjpa.service;

import io.github.ruan.springjpa.application.dto.AuthorDTO;
import io.github.ruan.springjpa.application.dto.AuthorResponseDTO;
import io.github.ruan.springjpa.model.entitys.Author;
import io.github.ruan.springjpa.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public UUID save(AuthorDTO author){
        return authorRepository.save(author.parseToAuthor()).getId();
    }

    public AuthorResponseDTO findById(String id){
        Optional<Author> author = authorRepository.findById(UUID.fromString(id));
         if(author.isPresent()){
             Author saved = author.get();
             new AuthorResponseDTO(saved.getId(), saved.getName(), saved.getDateBirth(),
                     saved.getNationality());
         }
         return null;
    }

    public void deleteById(String id){
        authorRepository.deleteById(UUID.fromString(id));
    }

    public List<AuthorResponseDTO> findAllOrFilter(String name, String nacionality){

        if(name != null && nacionality != null){
            return toAuthorResponseDTOList(authorRepository.
                    findByNameAndNacionality(name, nacionality));
        }

        if(name != null){
            return toAuthorResponseDTOList(authorRepository.findByName(name));
        }

        if(nacionality != null){
            return toAuthorResponseDTOList(authorRepository.findByNacionality(nacionality));
        }

        return toAuthorResponseDTOList(authorRepository.findAll());
    }

    public void replace(String id, AuthorDTO authorDTO){

        if (authorRepository.findById(UUID.fromString(id)).isEmpty()) {
            throw new IllegalArgumentException("author with is id not found");
        }

        Author author = authorDTO.parseToAuthor();
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
