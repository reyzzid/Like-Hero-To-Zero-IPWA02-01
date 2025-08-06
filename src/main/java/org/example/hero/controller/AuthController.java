package org.example.hero.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.hero.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginForm(HttpServletRequest request, Model model) {

        model.addAttribute("currentPath", request.getRequestURI());
        model.addAttribute("page", "login");
        model.addAttribute("pageTitle", "Like Zero To Hero - Login");
        return "index";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        if (password.length() > 72) {
            model.addAttribute("error", "Пароль слишком длинный");
            model.addAttribute("username", username);
            model.addAttribute("page", "login");
            return "index";
        }

        User user = userRepository.findByUsername(username);


        if (user == null) {
            model.addAttribute("error", " Benutzer nicht gefunden");
            model.addAttribute("username", username);
            model.addAttribute("page", "login");

            return "index";
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            model.addAttribute("error", "Falsches Passwort");
            model.addAttribute("username", username);
            model.addAttribute("page", "login");
            return "index";
        }

        session.setAttribute("user", user);
        return "redirect:/database";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/database";
    }
}


