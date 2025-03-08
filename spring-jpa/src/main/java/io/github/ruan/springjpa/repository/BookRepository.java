package io.github.ruan.springjpa.repository;

import io.github.ruan.springjpa.model.entitys.Author;
import io.github.ruan.springjpa.model.entitys.Book;
import io.github.ruan.springjpa.model.enums.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
    List<Book> findByAuthor(Author author);

    Book findByTitle(String title);

    Book findByIsBn(String isBn);

    Book findByTitleAndValue(String title, BigDecimal value);

    List<Book> findByTitleOrIsBn(String title, String isBn);

    List<Book> findByDatePublicationBetween(LocalDate start, LocalDate last);

    @Query("select b from Book b order by b.title, b.value")
    List<Book> listedBookOrderByTitleAndValue();

    @Query("select a from Book b join b.author a")
    List<Author> listedAuthorOfBook();

    @Query("select distinct b.title from Book b")
    List<String> listedTitleUniqueOfBook();

    @Query("""
            select b.gender 
                from Book b 
                    join b.author a 
                        where a.nationality like 'Brazilian' 
    """)
    List<String> listedByGenderOfBookWhereAuthorIsBrazilian();

}
