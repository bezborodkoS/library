package com.example.libraryrest.controller;

import com.example.libraryrest.model.Book;
import com.example.libraryrest.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) { return bookService.addBook(book); }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) { bookService.deleteBook(id); }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        return bookService.updateBook(id, book);
    }
}
