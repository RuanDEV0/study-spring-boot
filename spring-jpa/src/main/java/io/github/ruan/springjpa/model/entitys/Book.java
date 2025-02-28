package io.github.ruan.springjpa.model.entitys;

import io.github.ruan.springjpa.model.enums.Gender;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tb_book")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(length = 100, nullable = false)
    private String title;
    @Column(length = 20, nullable = false)
    private String isBn;
    @Column(nullable = false)
    private LocalDate date_publication;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private Gender gender;
    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal value;
    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn
    private Author authorId;
}
