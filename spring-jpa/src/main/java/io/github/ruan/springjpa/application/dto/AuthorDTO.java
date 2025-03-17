package io.github.ruan.springjpa.application.dto;

import io.github.ruan.springjpa.model.entitys.Author;

import java.time.LocalDate;

public record AuthorDTO(String name,
                        LocalDate dateBirth,
                        String nationality) {

    public Author parseToAuthor(){
        Author author = new Author();
        author.setName(this.name);
        author.setNationality(this.nationality);
        author.setDateBirth(this.dateBirth);
        return author;
    }
}
