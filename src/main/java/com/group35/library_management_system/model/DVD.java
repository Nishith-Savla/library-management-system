package com.group35.library_management_system.model;

import jakarta.persistence.Entity;

@Entity
public class DVD extends Item {
    public DVD() {
    }

    public DVD(String title, String author, String publisher) {
        super(title, author, publisher);
    }
}
