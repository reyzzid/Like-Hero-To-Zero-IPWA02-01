package org.example.hero.controller;
import org.example.hero.model.Emission;
import org.example.hero.service.EmissionService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EmissionsController {

    private final EmissionService emissionService;

    public EmissionsController(EmissionService emissionService) {
        this.emissionService = emissionService;
    }

    @GetMapping("/database")
    public String database(HttpServletRequest request, Model model,
                            @RequestParam(defaultValue = "0") int page,
                            @RequestParam(required = false) String country,
                            @RequestParam(defaultValue = "year") String sort,
                            @RequestParam(defaultValue = "desc") String dir){

        model.addAttribute("currentPath", request.getRequestURI());
        model.addAttribute("page", "database");
        model.addAttribute("pageTitle", "Like Zero To Hero - DataBasa");

        List<String> countries = emissionService.findUniqueCountries();
        model.addAttribute("countries", countries);

        int pageSize = 30;
        Sort.Direction direction = dir.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(direction, sort));

        if (country != null && !country.isEmpty()) {
            Page<Emission> filtered = emissionService.findByCountry(country, pageable);
            model.addAttribute("emissions", filtered.getContent());
            model.addAttribute("totalPages", filtered.getTotalPages());
            model.addAttribute("selectedCountry", country);
        } else {
            Page<Emission> all = emissionService.findAll(pageable);
            model.addAttribute("emissions", all.getContent());
            model.addAttribute("totalPages", all.getTotalPages());
        }

        model.addAttribute("currentPage", page);
        model.addAttribute("sortField", sort);
        model.addAttribute("sortDir", dir);

        return "index";
    }
}
