package org.example.hero.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.hero.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

import org.example.hero.model.User;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String loginForm(HttpServletRequest request, Model model) {
        model.addAttribute("currentPath", request.getRequestURI());
        model.addAttribute("page", "login");
        model.addAttribute("pageTitle", "Log In - Like Zero To Hero");
        return "index";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        HttpSession session) {

        User user = userRepository.findByUsername(username);

        session.setAttribute("user", user);
        return "redirect:/database";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/database";
    }
}


