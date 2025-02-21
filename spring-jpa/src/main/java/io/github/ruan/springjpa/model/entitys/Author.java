package io.github.ruan.springjpa.model.entitys;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_author")
@Data
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false, length = 50)
    private String nationality;
    @Column(nullable = false)
    private LocalDate date_birth;
    @OneToMany(mappedBy = "authorId")
    private Set<Book> listBook;
}
