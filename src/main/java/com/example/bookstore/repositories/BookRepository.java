package com.example.bookstore.repositories;

import com.example.bookstore.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("select b from Book b where b.category.id = ?1")
    Collection<Book> findByCategoryId(Long id);
    
    @Query("select b from Book b where b.category.name = ?1")
    Collection<Book> findByCategoryName(String name);

    @Modifying
    @Transactional
    @Query("DELETE FROM Book b WHERE b.category.id = ?1")
    void deleteByCategoryId(Long id);
}
