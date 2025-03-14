package io.github.ruan.springjpa.service;

import io.github.ruan.springjpa.application.dto.AuthorDTO;
import io.github.ruan.springjpa.model.entitys.Author;
import io.github.ruan.springjpa.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public UUID save(Author author){
        return authorRepository.save(author).getId();
    }
}
