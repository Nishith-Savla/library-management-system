package com.group35.library_management_system.model;

import jakarta.persistence.Entity;

@Entity
public class Book extends Item {
    public Book() {
    }

    public Book(String title, String author, String publisher) {
        super(title, author, publisher);
    }

}
