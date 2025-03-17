package io.github.ruan.springjpa.application;

import io.github.ruan.springjpa.application.dto.AuthorDTO;
import io.github.ruan.springjpa.application.dto.AuthorResponseDTO;
import io.github.ruan.springjpa.model.entitys.Author;
import io.github.ruan.springjpa.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("authors")
public class AuthorController {

    private final AuthorService authorServive;


    @PostMapping
    public ResponseEntity<Void> save(@RequestBody AuthorDTO authorDTO){
        UUID uuid = authorServive.save(authorDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(uuid)
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> replace(@PathVariable String id,
                                        @RequestBody AuthorDTO authorDTO){
        authorServive.replace(id, authorDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<AuthorResponseDTO> findById(@PathVariable String id){
        AuthorResponseDTO responseDTO = authorServive.findById(id);
        return responseDTO != null ? ResponseEntity.ok(responseDTO) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        authorServive.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<AuthorResponseDTO>> findAllOrFilter(@RequestParam(value = "name", required = false) String name,
                                                                   @RequestParam(value = "nacionality", required = false) String nacionality){
        return ResponseEntity.ok(authorServive.findAllOrFilter(name, nacionality));
    }
}
