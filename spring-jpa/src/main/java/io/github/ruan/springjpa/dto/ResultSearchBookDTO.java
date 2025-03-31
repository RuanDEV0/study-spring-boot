package io.github.ruan.springjpa.dto;

import io.github.ruan.springjpa.model.entitys.Author;
import io.github.ruan.springjpa.model.enums.Gender;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record ResultSearchBookDTO(
        UUID authorID,
        String title,
        String isBn,
        LocalDate datePublication,
        Gender gender,
        BigDecimal value,
        Author authorId) {
}
