package com.group35.library_management_system.repository;

import com.group35.library_management_system.model.CD;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CDRepository extends JpaRepository<CD, Long> {
}