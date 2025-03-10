package io.github.ruan.springjpa.repository;

import io.github.ruan.springjpa.model.entitys.Author;
import io.github.ruan.springjpa.model.entitys.Book;
import io.github.ruan.springjpa.model.enums.Gender;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * @see BookRepositoryTest
 */
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

    @Query("select b from Book b where b.gender = :nomeParametro order by :orderBy")
    List<Book> findByGenderOrderByValue(@Param("nomeParametro") Gender gender,
                                        @Param("orderBy") String type);

    @Query("select b from Book b where b.gender = ?1 order by ?2")
    List<Book> findByGenderOrderByTitle(Gender gender, String orderBy);

    @Modifying
    @Transactional
    @Query("delete Book where gender = ?1")
    void deleteByGender(Gender gender);

    @Modifying
    @Transactional
    @Query("update  Book set datePublication = ?1")
    void updateDatePublication(LocalDate date);
}
