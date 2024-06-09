package com.group35.library_management_system.repository;

import com.group35.library_management_system.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}