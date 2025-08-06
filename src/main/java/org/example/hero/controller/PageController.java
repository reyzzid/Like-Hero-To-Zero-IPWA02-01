package org.example.hero.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

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
        return "index";
    }

}
