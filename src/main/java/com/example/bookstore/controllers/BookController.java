package com.example.bookstore.controllers;

import com.example.bookstore.models.Book;
import com.example.bookstore.services.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;

    // GET all books
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    //GET book by id
    @GetMapping ("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    //create book
    @PostMapping
    public void createBook(@RequestBody Book book) {
        bookService.createBook(book);
    }

    //update book
    @PutMapping ("/{id}")
    public void updateBookById(@RequestBody Book book, @PathVariable Long id) {
        bookService.updateBook(book, id);
    }

    //delete book
    @DeleteMapping ("/{id}")
    public void deleteBookById(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    //GET books by categoryId
    @GetMapping ("/category/{bookId}")
    public Collection<Book> getBooksByCategoryId(@PathVariable Long bookId) {
        return bookService.getBooksByCategoryId(bookId);
    }

    //get book by category name
    @GetMapping("/search")
    public Collection<Book> getBooksByCategoryName(@RequestParam(value = "name", required = false) String categoryName) {
        return bookService.getBookByCategoryName(categoryName);
    }

}
