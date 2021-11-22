package com.example.bookstore.repositories;

import com.example.bookstore.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("select c from Category c join c.books b where b.id = ?1")
    Category findByBookId(Long id);

    @Query("select c from Category c join c.books b where b.name like %?1%")
    List<Category> findByName(String name);
}
