package com.group35.library_management_system.model;

import jakarta.persistence.Entity;

@Entity
public class CD extends Item {
    public CD() {
    }

    public CD(String title, String author, String publisher) {
        super(title, author, publisher);
    }
}
