package io.github.ruan.springjpa.service;

import io.github.ruan.springjpa.application.dto.AuthorDTO;
import io.github.ruan.springjpa.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public UUID save(AuthorDTO author){
        return authorRepository.save(author.parseToAuthor()).getId();
    }
}
