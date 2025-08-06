package org.example.hero.service;

import org.example.hero.model.Emission;
import org.example.hero.repository.EmissionsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmissionService {

    private final EmissionsRepository emissionRepository;

    public EmissionService(EmissionsRepository emissionRepository) {
        this.emissionRepository = emissionRepository;
    }

    public List<String> findUniqueCountries() {
        return emissionRepository.findAll()
                .stream()
                .map(Emission::getCountry)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public Page<Emission> findAll(Pageable pageable) {
        return emissionRepository.findAll(pageable);
    }

    public Page<Emission> findByCountry(String country, Pageable pageable) {
        return emissionRepository.findByCountry(country, pageable);
    }
}
