package com.group35.library_management_system.config;

import com.group35.library_management_system.model.*;
import com.group35.library_management_system.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DBInitializer_Items {
    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, BookRepository bookRepository, DVDRepository dvdRepository, CDRepository cdRepository, PeriodicalRepository periodicalRepository) {
        return args -> {
            // Create a list of sample users
            var users = List.of(
                    new User("Alice", "alice@example.com"),
                    new User("Bob", "bob@example.com"),
                    new User("Charlie", "charlie@example.com")
            );
            // Save all users to the database
            userRepository.saveAll(users);

            // Create a list of sample books
            var books = List.of(
                    new Book("The Great Gatsby", "F. Scott Fitzgerald", "Scribner"),
                    new Book("To Kill a Mockingbird", "Harper Lee", "J.B. Lippincott & Co."),
                    new Book("1984", "George Orwell", "Secker & Warburg")
            );

            // Save all books to the database
            bookRepository.saveAll(books);

            // Create a list of sample DVDs
            var dvds = List.of(
                    new DVD("The Shawshank Redemption", "Frank Darabont", "Castle Rock Entertainment"),
                    new DVD("The Godfather", "Francis Ford Coppola", "Paramount Pictures"),
                    new DVD("The Dark Knight", "Christopher Nolan", "Warner Bros. Pictures")
            );

            // Save all DVDs to the database
            dvdRepository.saveAll(dvds);

            // Create a list of sample CDs
            var cds = List.of(
                    new CD("Thriller", "Michael Jackson", "Epic Records"),
                    new CD("Back in Black", "AC/DC", "Albert Productions"),
                    new CD("The Dark Side of the Moon", "Pink Floyd", "Harvest Records")
            );

            // Save all CDs to the database
            cdRepository.saveAll(cds);

            // Create a list of sample periodicals
            var periodicals = List.of(
                    new Periodical("National Geographic", "National Geographic Society", "Monthly"),
                    new Periodical("Time", "Time USA, LLC", "Weekly"),
                    new Periodical("The New Yorker", "Condé Nast", "Weekly")
            );

            // Save all periodicals to the database
            periodicalRepository.saveAll(periodicals);
        };
    }
}
