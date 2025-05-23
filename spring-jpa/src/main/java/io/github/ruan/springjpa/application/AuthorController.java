package io.github.ruan.springjpa.application;

import io.github.ruan.springjpa.dto.AuthorDTO;
import io.github.ruan.springjpa.dto.AuthorResponseDTO;
import io.github.ruan.springjpa.dto.ResponseError;
import io.github.ruan.springjpa.exception.RegisterDuplicateException;
import io.github.ruan.springjpa.service.AuthorService;
import jakarta.validation.Valid;
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
    public ResponseEntity<Object> save(@RequestBody @Valid AuthorDTO authorDTO){
        try{
            UUID uuid = authorServive.save(authorDTO);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(uuid)
                    .toUri();
            return ResponseEntity.created(uri).build();
        }catch(RegisterDuplicateException e){
            var error = ResponseError.conflict(e.getMessage());
            return ResponseEntity.status(error.statusCode()).body(error);
        }

    }

    @PutMapping("{id}")
    public ResponseEntity<Object> replace(@PathVariable String id,
                                               @RequestBody @Valid AuthorDTO authorDTO){
        try{
            authorServive.replace(id, authorDTO);
            return ResponseEntity.noContent().build();
        }catch(RegisterDuplicateException e){
            var error = ResponseError.conflict(e.getMessage());
            return ResponseEntity.status(error.statusCode()).body(error);
        }
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
                                                                   @RequestParam(value = "nationality", required = false) String nationality){
        return ResponseEntity.ok(authorServive.findAllOrFilter(name, nationality));
    }
}
