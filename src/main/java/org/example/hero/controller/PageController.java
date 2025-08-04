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

    @GetMapping("/login")
    public String login(HttpServletRequest request, Model model) {
        model.addAttribute("currentPath", request.getRequestURI());
        model.addAttribute("page", "login");
        model.addAttribute("pageTitle", "Like Zero To Hero - Login");
        return "index";

    }

    @GetMapping("/database")
    public String database(HttpServletRequest request, Model model) {
        model.addAttribute("currentPath", request.getRequestURI());
        model.addAttribute("page", "database");
        model.addAttribute("pageTitle", "Like Zero To Hero - DataBasa");
        return "index";



    }
}
