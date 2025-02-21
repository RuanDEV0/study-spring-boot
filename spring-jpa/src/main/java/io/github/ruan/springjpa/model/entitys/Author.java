package io.github.ruan.springjpa.model.entitys;

import jakarta.persistence.*;
import lombok.Setter;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tb_author")
@Getter
@Setter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String nationality;
    @Column(nullable = false)
    private LocalDate date_birth;
}
