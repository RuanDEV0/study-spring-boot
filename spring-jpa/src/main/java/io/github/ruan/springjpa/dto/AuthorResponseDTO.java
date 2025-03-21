package io.github.ruan.springjpa.dto;

import io.github.ruan.springjpa.model.entitys.Author;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record AuthorResponseDTO (UUID id,
                                 @NotBlank
                                 @Size(min = 2, max = 100)
                                 String name,
                                 @NotNull
                                 @Past
                                 LocalDate dateBirth,
                                 @NotBlank
                                 @Size(min = 2, max = 50)
                                 String nationality){

    public Author parseToAuthor(){
        Author author = new Author();
        author.setId(id);
        author.setName(this.name);
        author.setNationality(this.nationality);
        author.setDateBirth(this.dateBirth);
        return author;
    }
}
