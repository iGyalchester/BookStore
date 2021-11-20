package com.example.bookstore.repositories;

import com.example.bookstore.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
//    @Query("select c from Category c where c.book.id = ?1")
//    Category findByBookId(Long id);
//
//    @Modifying
//    @Transactional
//    @Query("DELETE FROM Book b WHERE b.category.id = ?1")
//    void deleteByBookId(Long id);
}
