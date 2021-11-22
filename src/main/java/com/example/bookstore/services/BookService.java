package com.example.bookstore.services;

import com.example.bookstore.models.Book;
import com.example.bookstore.models.Category;
import com.example.bookstore.repositories.BookRepository;
import com.example.bookstore.repositories.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class BookService{

    @Autowired
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);


    public BookService(BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }

    //get all books
    public List<Book> getAllBooks() {
        List<Book> books = bookRepository.findAll();

        if (books.isEmpty()) {
            logger.info("No books found");
            return null;
        }
        logger.info("Books found");
        return books;
    }

    //get a book by id
    public Book getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (bookRepository.findById(id).isEmpty()) {
            logger.info("Book not found");
            return null;
        }
        logger.info("Book found");
        return book.get();
    }

    //PUT book
    public void updateBook(Book book, Long id) {
        Optional<Category> categories = categoryRepository
                .findById(book.getCategory().getId());
        Optional<Book> books = bookRepository.findById(id);

        if (categories.isEmpty()) {
            logger.info("Category not found");
        }

        if (bookRepository.findById(id).isEmpty()) {
            logger.info("Book not found");
        }

        book.setCategory(categories.get());
        book.setId(books.get().getId());

        bookRepository.save(book);

        logger.info("Book updated");
    }

    //DELETE book
    public void deleteBook(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            logger.info("Book not found");
        }

        logger.info("Book deleted");
        bookRepository.delete(book.get());

    }

    //POST a book
    public Book createBook(Book book) {
        Optional<Category> categories = categoryRepository
                .findById(book.getCategory().getId());

        if (categories.isEmpty()) {
            logger.info("Category is Empty found");
            return null;
        }

        book.setCategory(categories.get());
        Book newBook = bookRepository.save(book);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newBook.getId()).toUri();

        logger.info("Book created/Found");
        return newBook;
    }

    //GET books by categoryId
    public Collection<Book> getBooksByCategoryId(Long id) {
        return bookRepository.findByCategoryId(id);
    }

    //GET books by categoryName
    public Collection<Book>getBookByCategoryName(String name) {
        return bookRepository.findByCategoryNameContainsIgnoreCase(name);
    }
}
