package io.github.ruan.springjpa.application;

import io.github.ruan.springjpa.application.dto.AuthorDTO;
import io.github.ruan.springjpa.model.entitys.Author;
import io.github.ruan.springjpa.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorServive;


    @PostMapping
    public ResponseEntity<Void> save(@RequestBody AuthorDTO authorDTO){
        UUID uuid = authorServive.save(authorDTO.parseToAuthor());

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(uuid)
                .toUri();

        return ResponseEntity.created(uri).build();
    }
}
