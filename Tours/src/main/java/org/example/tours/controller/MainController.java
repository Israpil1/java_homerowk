package org.example.tours.controller;

import org.example.tours.entity.Tour;
import org.example.tours.entity.User;
import org.example.tours.repository.TourRepository;
import org.example.tours.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private TourRepository tourRepository;

    @GetMapping("/")
    public String home(Model model) {
        // Достаем все туры из MySQL и передаем в HTML под именем "tours"
        model.addAttribute("tours", tourRepository.findAll());
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/login"; // После успеха идем на логин
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}