package com.example.bookstore.controllers;

import com.example.bookstore.models.Book;
import com.example.bookstore.services.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;

    // GET all books
    @GetMapping
    public ResponseEntity<?> getAllBooks() {
        return bookService.getAllBooks();
    }

    //GET book by id
    @GetMapping ("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    //create book
    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    //update book
    @PutMapping ("/{id}")
    public ResponseEntity<?> updateBookById(@RequestBody Book book, @PathVariable Long id) {
        return bookService.updateBook(book, id);
    }

    //delete book
    @DeleteMapping ("/{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable Long id) {
        return bookService.deleteBook(id);
    }

    //GET books by categoryId
    @GetMapping ("/category/{bookId}")
    public ResponseEntity<?> getBooksByCategoryId(@PathVariable Long bookId) {
        return bookService.getBookByCategoryId(bookId);
    }
}
