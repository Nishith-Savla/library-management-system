package com.group35.library_management_system.model;

import jakarta.persistence.Entity;

@Entity
public class Periodical extends Item {
    public Periodical() {
    }

    public Periodical(String title, String author, String publisher) {
        super(title, author, publisher);
    }
}
