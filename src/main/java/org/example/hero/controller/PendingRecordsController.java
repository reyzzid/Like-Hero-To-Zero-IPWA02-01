package org.example.hero.controller;

import org.example.hero.model.Emission;
import org.example.hero.model.PendingRecords;
import org.example.hero.repository.EmissionsRepository;
import org.example.hero.repository.PendingRecordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PendingRecordsController {

    @Autowired
    private PendingRecordsRepository pendingRecordsRepository;

    @Autowired
    private EmissionsRepository emissionsRepository;

    @PostMapping("/edit/publish")
    public String publishRecord(@RequestParam Long id) {
        PendingRecords pending = pendingRecordsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid id: " + id));

        Emission emission = new Emission();
        emission.setCountry(pending.getCountry());
        emission.setYear(pending.getYear());
        emission.setEmissions(pending.getEmissions());

        emissionsRepository.save(emission);
        pendingRecordsRepository.delete(pending);

        return "redirect:/edit#pending";
    }

    @PostMapping("/edit/delete")
    public String deleteRecord(@RequestParam Long id) {
        pendingRecordsRepository.deleteById(id);
        return "redirect:/edit#pending";
    }

}
