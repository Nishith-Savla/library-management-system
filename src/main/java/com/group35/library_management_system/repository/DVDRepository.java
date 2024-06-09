package com.group35.library_management_system.repository;

import com.group35.library_management_system.model.DVD;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DVDRepository extends JpaRepository<DVD, Long> {
}