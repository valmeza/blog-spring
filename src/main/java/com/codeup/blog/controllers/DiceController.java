package com.codeup.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DiceController {

    @GetMapping("/roll-dice")
    public String dice() {
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{n}")
    public String diceNumber(@PathVariable int n, Model model) {
        model.addAttribute("n", n);
        return "roll-dice";
    }
}
