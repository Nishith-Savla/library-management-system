package com.group35.library_management_system.repository;

import com.group35.library_management_system.model.BorrowingEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowingEntryRepository extends JpaRepository<BorrowingEntry, Long> {
}
