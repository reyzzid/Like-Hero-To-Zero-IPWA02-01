package org.example.hero.repository;

import org.example.hero.model.Emission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmissionRepository extends JpaRepository<Emission, Long> {
    Page<Emission> findByCountry(String country, Pageable pageable);
}
