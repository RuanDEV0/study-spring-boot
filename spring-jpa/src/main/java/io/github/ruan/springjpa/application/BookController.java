package io.github.ruan.springjpa.application;

import io.github.ruan.springjpa.dto.RegisterBookDTO;
import io.github.ruan.springjpa.dto.ResponseError;
import io.github.ruan.springjpa.exception.RegisterDuplicateException;
import io.github.ruan.springjpa.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid RegisterBookDTO registerBookDTO){
        try{
            return ResponseEntity.ok(registerBookDTO);
        }catch (RegisterDuplicateException e){
            var erroDTO = ResponseError.conflict(e.getMessage());
            return ResponseEntity.status(erroDTO.statusCode()).body(erroDTO);
        }
    }
}
