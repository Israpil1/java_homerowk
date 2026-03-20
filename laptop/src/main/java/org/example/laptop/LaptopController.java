package org.example.laptop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LaptopController {

    @Autowired
    private LaptopRepository repository;

    @GetMapping("/add")
    public String showForm(Model model) {
        model.addAttribute("laptop", new Laptop());
        return "laptop-form";
    }

    @PostMapping("/save")
    public String saveLaptop(@ModelAttribute Laptop laptop) {
        repository.save(laptop);
        return "redirect:/add?success";
    }
}