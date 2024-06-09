package com.group35.library_management_system.controller;

import com.group35.library_management_system.model.CD;
import com.group35.library_management_system.repository.CDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Indicates that this class is a Spring MVC controller
@RequestMapping("/api/cds") // Maps HTTP requests to /api/users to this class
public class CDController {

    @Autowired
    private CDRepository cdRepository;

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
}
