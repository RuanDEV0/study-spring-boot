package io.github.ruan.springjpa.application.dto;

import io.github.ruan.springjpa.model.entitys.Author;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AuthorDTO(
        @NotBlank
        String name,
        @NotNull
        LocalDate dateBirth,
        @NotBlank
        String nationality) {

    public Author parseToAuthor(){
        Author author = new Author();
        author.setName(this.name);
        author.setNationality(this.nationality);
        author.setDateBirth(this.dateBirth);
        return author;
    }
}
