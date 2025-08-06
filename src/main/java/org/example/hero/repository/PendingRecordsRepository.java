package org.example.hero.repository;

import org.example.hero.model.PendingRecords;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PendingRecordsRepository extends JpaRepository<PendingRecords, Long> {
}