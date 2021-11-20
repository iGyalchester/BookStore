package com.example.bookstore.repositories;

import com.example.bookstore.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByCategoryId(Long id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Book b WHERE b.category.id = ?1")
    void deleteByCategoryId(Long id);

}
