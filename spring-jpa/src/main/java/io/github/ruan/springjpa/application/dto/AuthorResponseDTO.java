package io.github.ruan.springjpa.application.dto;

import io.github.ruan.springjpa.model.entitys.Author;

import java.time.LocalDate;
import java.util.UUID;

public record AuthorResponseDTO (UUID id,
                                 String name,
                                 LocalDate dateBirth,
                                 String nacionality){

    public Author parseToAuthor(){
        Author author = new Author();
        author.setId(id);
        author.setName(this.name);
        author.setNationality(this.nacionality);
        author.setDateBirth(this.dateBirth);
        return author;
    }
}
