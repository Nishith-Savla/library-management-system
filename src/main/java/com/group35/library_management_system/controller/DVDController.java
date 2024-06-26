package com.group35.library_management_system.controller;

import com.group35.library_management_system.model.DVD;
import com.group35.library_management_system.model.BorrowingEntry;
import com.group35.library_management_system.model.DVD;
import com.group35.library_management_system.repository.BorrowingEntryRepository;
import com.group35.library_management_system.repository.DVDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController // Indicates that this class is a Spring MVC controller
@CrossOrigin
@RequestMapping("/api/dvds") // Maps HTTP requests to /api/users to this class
public class DVDController {

    @Autowired
    private DVDRepository dvdRepository;

    @Autowired
    private BorrowingEntryRepository borrowingEntryRepository;


    @GetMapping // Maps HTTP GET requests to /api/users to this method
    public List<DVD> getAllDVDs() {
        /*
            Fetches and returns all DVD entities from the database
         */
        return dvdRepository.findAll();
    }

    @GetMapping("/{id}") // Maps HTTP GET requests to /api/users to this method
    public DVD getDVDById(@PathVariable Long id) {
        /*
            Fetches and returns the DVD entity with the given id from the database
         */
        return dvdRepository.findById(id).orElse(null);
    }

    @PostMapping // Maps HTTP POST requests to /api/users to this method
    public DVD addDVD(@RequestBody DVD dvd) {
        /*
            Saves the given DVD entity to the database
         */
        return dvdRepository.save(dvd);
    }

    @PutMapping("/{id}") // Maps HTTP PUT requests to /api/users to this method
    public DVD updateDVD(@PathVariable Long id, @RequestBody DVD dvd) {
        /*
            Updates the DVD entity with the given id in the database
         */
        DVD existingDVD = dvdRepository.findById(id).orElse(null);
        if (existingDVD != null) {
            existingDVD.setTitle(dvd.getTitle());
            existingDVD.setAuthor(dvd.getAuthor());
            existingDVD.setPublisher(dvd.getPublisher());
            return dvdRepository.save(existingDVD);
        }
        return null;
    }

    @DeleteMapping("/{id}") // Maps HTTP DELETE requests to /api/users to this method
    public void deleteDVD(@PathVariable Long id) {
        /*
            Deletes the DVD entity with the given id from the database
         */
        dvdRepository.deleteById(id);
    }

    @PostMapping("/{id}/borrow") // Maps HTTP POST requests to /api/users to this method
    public BorrowingEntry borrowDVD(@PathVariable long id, @RequestBody long userId) {
        /*
            Creates a new BorrowingEntry entity in the database
         */
        BorrowingEntry borrowingEntry = borrowingEntryRepository.findById(id).orElse(null);
        if (borrowingEntry == null || borrowingEntry.getReturnDate() != null) {
            DVD dvd = dvdRepository.findById(id).orElse(null);
            if (dvd != null) {
                dvdRepository.save(dvd);
                return borrowingEntryRepository.save(new BorrowingEntry(userId, id, new Date().toString(), null));
            }
        }
        return null;
    }

    @PostMapping("/{id}/return") // Maps HTTP POST requests to /api/users to this method
    public BorrowingEntry returnDVD(@PathVariable long id, @RequestBody long userId) {
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
