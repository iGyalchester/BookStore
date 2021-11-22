package com.example.bookstore.services;

import com.example.bookstore.models.Category;
import com.example.bookstore.repositories.BookRepository;
import com.example.bookstore.repositories.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;

    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, BookRepository bookRepository) {
        this.categoryRepository = categoryRepository;
        this.bookRepository = bookRepository;
    }

    //POST new category
    public Category createCategory(Category category) {
        Category newCategory = categoryRepository.save(category);

        URI newCategoryUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newCategory.getId()).toUri();

        logger.info("Category created");

        return newCategory;
    }

    //GET all categories
    public List<Category> getAllCategories() {

        List<Category> categories = categoryRepository.findAll();
        if (categories == null) {
            logger.info("No Categories Found");
            return null;
        }
        logger.info("Category found");
        return categories;
    }

    //GET category by id
    public Optional<Category> getCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            logger.info("Category not found");
            return null;
        }

        logger.info("Category found");
        return category;
    }

    //PUT
    public Category updateCategory(Category category,Long id) {
        Optional<Category> findByCategoryId = categoryRepository.findById(id);
        if (findByCategoryId.isEmpty()) {
            logger.info("Category not found");
            return null;
        }

        logger.info("Category updated");
        category.setId(findByCategoryId.get().getId());
        categoryRepository.save(category);

        return categoryRepository.save(category);
    }

    //DELETE category
    public void deleteCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            logger.info("Category not found");
        }

        deleteCategoryWithBooks(category.get());

        logger.info("Category deleted");
    }


    @Transactional
    public void deleteCategoryWithBooks(Category category) {
        bookRepository.deleteByCategoryId(category.getId());
        categoryRepository.delete(category);
    }

    //get a book's category by USING the book's id
    public Category getCategoryByBookId(Long bookId) {
        return (categoryRepository.findByBookId(bookId));
    }

    //get book by keyword
    public List<Category> getBookByKeyword(String name) {
        return categoryRepository.findByName(name);
    }
}
