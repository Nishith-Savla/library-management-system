package com.group35.library_management_system.controller;

import com.group35.library_management_system.model.BorrowingEntry;
import com.group35.library_management_system.model.CD;
import com.group35.library_management_system.repository.BorrowingEntryRepository;
import com.group35.library_management_system.repository.CDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController // Indicates that this class is a Spring MVC controller
@CrossOrigin
@RequestMapping("/api/cds") // Maps HTTP requests to /api/users to this class
public class CDController {

    @Autowired
    private CDRepository cdRepository;

    @Autowired
    private BorrowingEntryRepository borrowingEntryRepository;


    @GetMapping // Maps HTTP GET requests to /api/users to this method
    public List<CD> getAllCDs() {
        /*
            Fetches and returns all CD entities from the database
         */
        return cdRepository.findAll();
    }

    @GetMapping("/{id}") // Maps HTTP GET requests to /api/users to this method
    public CD getCDById(@PathVariable Long id) {
        /*
            Fetches and returns the CD entity with the given id from the database
         */
        return cdRepository.findById(id).orElse(null);
    }

    @PostMapping // Maps HTTP POST requests to /api/users to this method
    public CD addCD(@RequestBody CD cd) {
        /*
            Saves the given CD entity to the database
         */
        return cdRepository.save(cd);
    }

    @PutMapping("/{id}") // Maps HTTP PUT requests to /api/users to this method
    public CD updateCD(@PathVariable Long id, @RequestBody CD cd) {
        /*
            Updates the CD entity with the given id in the database
         */
        CD existingCD = cdRepository.findById(id).orElse(null);
        if (existingCD != null) {
            existingCD.setTitle(cd.getTitle());
            existingCD.setAuthor(cd.getAuthor());
            existingCD.setPublisher(cd.getPublisher());
            return cdRepository.save(existingCD);
        }
        return null;
    }

    @DeleteMapping("/{id}") // Maps HTTP DELETE requests to /api/users to this method
    public void deleteCD(@PathVariable Long id) {
        /*
            Deletes the CD entity with the given id from the database
         */
        cdRepository.deleteById(id);
    }

    @PostMapping("/{id}/borrow") // Maps HTTP POST requests to /api/users to this method
    public BorrowingEntry borrowCD(@PathVariable long id, @RequestBody long userId) {
        /*
            Creates a new BorrowingEntry entity in the database
         */
        BorrowingEntry borrowingEntry = borrowingEntryRepository.findById(id).orElse(null);
        if (borrowingEntry == null || borrowingEntry.getReturnDate() != null) {
            CD cd = cdRepository.findById(id).orElse(null);
            if (cd != null) {
                cdRepository.save(cd);
                return borrowingEntryRepository.save(new BorrowingEntry(userId, id, new Date().toString(), null));
            }
        }
        return null;
    }

    @PostMapping("/{id}/return") // Maps HTTP POST requests to /api/users to this method
    public BorrowingEntry returnCD(@PathVariable long id, @RequestBody long userId) {
        /*
            Updates the BorrowingEntry entity with the given id in the database
         */
        BorrowingEntry borrowingEntry = borrowingEntryRepository.findById(id).orElse(null);
        if (borrowingEntry != null && borrowingEntry.getUserId() == userId && borrowingEntry.getReturnDate() == null) {
            borrowingEntry.setReturnDate(new Date().toString());
            return borrowingEntryRepository.save(borrowingEntry);
        }
        return null;
    }
}
