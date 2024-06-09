package com.group35.library_management_system.controller;

import com.group35.library_management_system.model.Periodical;
import com.group35.library_management_system.repository.PeriodicalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Indicates that this class is a Spring MVC controller
@RequestMapping("/api/periodicals") // Maps HTTP requests to /api/users to this class
public class PeriodicalController {

    @Autowired
    private PeriodicalRepository periodicalRepository;

    @GetMapping // Maps HTTP GET requests to /api/users to this method
    public List<Periodical> getAllPeriodicals() {
        /*
            Fetches and returns all Periodical entities from the database
         */
        return periodicalRepository.findAll();
    }

    @GetMapping("/{id}") // Maps HTTP GET requests to /api/users to this method
    public Periodical getPeriodicalById(@PathVariable Long id) {
        /*
            Fetches and returns the Periodical entity with the given id from the database
         */
        return periodicalRepository.findById(id).orElse(null);
    }

    @PostMapping // Maps HTTP POST requests to /api/users to this method
    public Periodical addPeriodical(@RequestBody Periodical periodical) {
        /*
            Saves the given Periodical entity to the database
         */
        return periodicalRepository.save(periodical);
    }

    @PutMapping("/{id}") // Maps HTTP PUT requests to /api/users to this method
    public Periodical updatePeriodical(@PathVariable Long id, @RequestBody Periodical periodical) {
        /*
            Updates the Periodical entity with the given id in the database
         */
        Periodical existingPeriodical = periodicalRepository.findById(id).orElse(null);
        if (existingPeriodical != null) {
            existingPeriodical.setTitle(periodical.getTitle());
            existingPeriodical.setAuthor(periodical.getAuthor());
            existingPeriodical.setPublisher(periodical.getPublisher());
            return periodicalRepository.save(existingPeriodical);
        }
        return null;
    }

    @DeleteMapping("/{id}") // Maps HTTP DELETE requests to /api/users to this method
    public void deletePeriodical(@PathVariable Long id) {
        /*
            Deletes the Periodical entity with the given id from the database
         */
        periodicalRepository.deleteById(id);
    }
}
