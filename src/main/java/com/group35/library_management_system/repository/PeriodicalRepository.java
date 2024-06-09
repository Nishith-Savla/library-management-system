package com.group35.library_management_system.repository;

import com.group35.library_management_system.model.Periodical;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeriodicalRepository extends JpaRepository<Periodical, Long> {
}