package org.example.test_balandzio_15.repositories;


import org.example.test_balandzio_15.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByGenreAndYears(String genre, Integer years);

    List<Book> findByGenre(String genre);

    Optional<Book> findById(Long id);

    void deleteById(Long id);

}
