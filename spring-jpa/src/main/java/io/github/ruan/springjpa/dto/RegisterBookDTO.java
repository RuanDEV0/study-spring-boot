package io.github.ruan.springjpa.dto;

import io.github.ruan.springjpa.model.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.ISBN;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RegisterBookDTO(
        @NotBlank
        String title,
        @NotBlank
        @ISBN
        String isBn,
        @Past
        @NotNull
        LocalDate datePublication,
        Gender gender,
        BigDecimal value) {
}
