package com.group35.library_management_system.controller;

import com.group35.library_management_system.model.Book;
import com.group35.library_management_system.model.BorrowingEntry;
import com.group35.library_management_system.repository.BookRepository;
import com.group35.library_management_system.repository.BorrowingEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController // Indicates that this class is a Spring MVC controller
@RequestMapping("/api/books") // Maps HTTP requests to /api/users to this class
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BorrowingEntryRepository borrowingEntryRepository;

    @GetMapping // Maps HTTP GET requests to /api/users to this method
    public List<Book> getAllBooks() {
        /*
            Fetches and returns all Book entities from the database
         */
        return bookRepository.findAll();
    }

    @GetMapping("/{id}") // Maps HTTP GET requests to /api/users to this method
    public Book getBookById(@PathVariable Long id) {
        /*
            Fetches and returns the Book entity with the given id from the database
         */
        return bookRepository.findById(id).orElse(null);
    }

    @PostMapping // Maps HTTP POST requests to /api/users to this method
    public Book addBook(@RequestBody Book book) {
        /*
            Saves the given Book entity to the database
         */
        return bookRepository.save(book);
    }

    @PutMapping("/{id}") // Maps HTTP PUT requests to /api/users to this method
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        /*
            Updates the Book entity with the given id in the database
         */
        Book existingBook = bookRepository.findById(id).orElse(null);
        if (existingBook != null) {
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());
            existingBook.setPublisher(book.getPublisher());
            return bookRepository.save(existingBook);
        }
        return null;
    }

    @DeleteMapping("/{id}") // Maps HTTP DELETE requests to /api/users to this method
    public void deleteBook(@PathVariable Long id) {
        /*
            Deletes the Book entity with the given id from the database
         */
        bookRepository.deleteById(id);
    }

    @PostMapping("/{id}/borrow") // Maps HTTP POST requests to /api/users to this method
    public BorrowingEntry borrowBook(@PathVariable long id, @RequestBody long userId) {
        /*
            Creates a new BorrowingEntry entity in the database
         */
        BorrowingEntry borrowingEntry = borrowingEntryRepository.findById(id).orElse(null);
        if (borrowingEntry == null) {
            Book book = bookRepository.findById(id).orElse(null);
            if (book != null) {
                bookRepository.save(book);
                return borrowingEntryRepository.save(new BorrowingEntry(userId, id, new Date().toString(), null));
            }
        }
        return null;
    }

    @PostMapping("/{id}/return") // Maps HTTP POST requests to /api/users to this method
    public void returnBook(@PathVariable long id, @RequestBody long userId) {
        /*
            Updates the BorrowingEntry entity with the given id in the database
         */
        BorrowingEntry borrowingEntry = borrowingEntryRepository.findById(id).orElse(null);
        if (borrowingEntry != null && borrowingEntry.getUserId() == userId) {
            borrowingEntry.setReturnDate(new Date().toString());
            borrowingEntryRepository.save(borrowingEntry);
        }
    }
}
