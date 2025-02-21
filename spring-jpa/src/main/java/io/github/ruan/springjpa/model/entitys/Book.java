package io.github.ruan.springjpa.model.entitys;

import io.github.ruan.springjpa.model.enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tb_book")
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String isBn;
    @Column(nullable = false)
    private LocalDate date_publication;
    @Column(nullable = false)
    private Gender gender;
    private Double value;
    @Column(nullable = false)
    private UUID authorId;
}
