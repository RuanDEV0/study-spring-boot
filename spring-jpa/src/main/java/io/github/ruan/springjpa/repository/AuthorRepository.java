package io.github.ruan.springjpa.repository;

import io.github.ruan.springjpa.model.entitys.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {
    List<Author> findByName(String name);
    List<Author> findByNacionality(String nacionality);
    List<Author> findByNameAndNacionality(String name, String nacionality);
}
