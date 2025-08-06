package org.example.hero.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.hero.model.PendingRecords;
import org.example.hero.repository.PendingRecordsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {

    private final PendingRecordsRepository pendingRecordsRepository;

    public PageController(PendingRecordsRepository pendingRecordsRepository) {
        this.pendingRecordsRepository = pendingRecordsRepository;
    }

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {
        model.addAttribute("currentPath", request.getRequestURI());
        model.addAttribute("page", "home");
        model.addAttribute("pageTitle", "Like Zero To Hero");
        return "index";
    }

    @GetMapping("/edit")
    public String editPage(HttpServletRequest request, Model model) {
        model.addAttribute("currentPath", request.getRequestURI());
        model.addAttribute("page", "edit");
        model.addAttribute("pageTitle", "Like Zero To Hero - New Record");

        model.addAttribute("pendingRecords", pendingRecordsRepository.findAll());

        return "index";
    }

    @PostMapping("/edit")
    public String submitRecord(
            @RequestParam String country,
            @RequestParam int year,
            @RequestParam long emissions
    ) {
        PendingRecords record = new PendingRecords();
        record.setCountry(country);
        record.setYear(year);
        record.setEmissions(emissions);
        pendingRecordsRepository.save(record);

        return "redirect:/edit#pending";
    }

}
